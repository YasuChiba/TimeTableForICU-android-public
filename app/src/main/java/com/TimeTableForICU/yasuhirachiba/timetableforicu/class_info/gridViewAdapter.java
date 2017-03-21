package com.TimeTableForICU.yasuhirachiba.timetableforicu.class_info;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.TimeTableForICU.yasuhirachiba.timetableforicu.R;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.SquareImageView;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.SquareTextView;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_entity_pict;

import java.util.ArrayList;

/**
 * Created by YasuhiraChiba on 16/08/26.
 */
public class gridViewAdapter extends ArrayAdapter<db_entity_pict> {

    private int resourceId;
    private class_info_ImageView_TagsEntity tagEntity;
    private Context mContext;
    private ItemClickListener itemClickListener;
    private ItemClickListener itemLongClickListener;
    private int Date;
    private int CountDateOrder;

    public gridViewAdapter(Context context, int resource, ArrayList<db_entity_pict> objects,int date,int countdateorder) {
        super(context, resource, objects);
        resourceId = resource;
        mContext=context;
        Date=date;
        CountDateOrder=countdateorder;

    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    public void setLongClickListener(ItemClickListener itemClickListener) {
        this.itemLongClickListener = itemClickListener;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resourceId, null);
        }




        if(getItem(position).getUri().equals("end")){


            final SquareTextView view=new SquareTextView(mContext);
            Typeface font = Typeface.createFromAsset(mContext.getAssets(), "fontawesome-webfont-v450.ttf");
            view.setTypeface(font);
            view.setGravity(Gravity.CENTER);
            view.setText(R.string.icon_plus);
            view.setBackgroundColor(Color.GRAY);

            class_info_ImageView_TagsEntity tagtmp=new class_info_ImageView_TagsEntity();

            tagtmp.setCountPictOrder(-1);
            tagtmp.setCountDateOrder(CountDateOrder);
            tagtmp.setDate(Date);
            tagtmp.setPictId(-1);

            view.setTag(tagtmp);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onClick(v,-1);

                }
            });


            return view;

        }
        else{

            SquareImageView view=new SquareImageView(mContext);
            Bitmap bitmap;
            Uri imageUri = Uri.parse(getItem(position).getUri());
            bitmap = getThubmnail(imageUri);


            view.setImageBitmap(bitmap);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);

            class_info_ImageView_TagsEntity tagtmp=new class_info_ImageView_TagsEntity();

            tagtmp.setCountPictOrder(position);
            tagtmp.setCountDateOrder(CountDateOrder);
            tagtmp.setDate(Date);
            tagtmp.setPictId(getItem(position).getId());

            view.setTag(tagtmp);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onClick(v,position);
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    class_info_ImageView_TagsEntity tag=(class_info_ImageView_TagsEntity)v.getTag();
                    Log.d("tagentity on LongclickL",String.valueOf(tag.getCountPictOrder()));
                    remove(getItem(tag.getCountPictOrder()));
                    itemLongClickListener.onClick(v,position);

                    return true;
                }
            });


            return view;

        }





    }





    Bitmap thumbnail;


    public Bitmap getThubmnail(Uri uri){


       // Bitmap thumbnail;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
       /* options.outWidth=100;
        options.outHeight=100;*/
        //  Bitmap image = BitmapFactory.decodeFile(String.valueOf(uri), options);
        try {
            ContentResolver resolver = mContext.getContentResolver();
            Cursor cursor = resolver.query(uri,null,null,null,null);
            int numcount=cursor.getCount();
            if(numcount>0){
                if(cursor.moveToFirst()) {
                    long id = cursor.getLong(cursor.getColumnIndex("_id"));
                    thumbnail = MediaStore.Images.Thumbnails.getThumbnail(resolver, id, MediaStore.Images.Thumbnails.MINI_KIND,options);


                }
                else {
                    Bitmap bmp1 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_menu_camera);
                    thumbnail=bmp1;
                }



            }

        } catch (Exception e) {
            ContentResolver resolver = mContext.getContentResolver();

            long id;
            try {
                if (DocumentsContract.isDocumentUri(mContext, uri)) {
                    String wholeID = DocumentsContract.getDocumentId(uri);
                    // Split at colon, use second item in the array
                    id = Long.parseLong(wholeID.split(":")[1]);
                } else if (isMediaUri(uri)) {
                    id = ContentUris.parseId(uri);
                } else return null;
                return MediaStore.Images.Thumbnails.getThumbnail(
                        resolver,
                        id,
                        MediaStore.Images.Thumbnails.MICRO_KIND,
                        null);
            } catch (Exception e1) {
                Bitmap bmp1 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_menu_camera);
                thumbnail=bmp1;
            }
        }





        return thumbnail;

    }



    public static boolean isMediaUri(Uri uri) {
        return "media".equalsIgnoreCase(uri.getAuthority());
    }


}