package com.sample.vdapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class VideoURLAdapter extends BaseAdapter{

//	private List<String> mList;
	private Context mContext;
	private String[] mList;
	private LayoutInflater inflater;
	
	public VideoURLAdapter(Context Context ,/* ArrayList<String>*/String[] list) {
		this.mContext = Context;
		this.mList = list;
		inflater = LayoutInflater.from(this.mContext);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		 MyViewHolder mViewHolder;
		 
	        if (convertView == null) {
	            convertView = inflater.inflate(R.layout.list_item, parent, false);
	            mViewHolder = new MyViewHolder(convertView);
	            convertView.setTag(mViewHolder);
	        } else {
	            mViewHolder = (MyViewHolder) convertView.getTag();
	        }
	 
//	        mList[position] = getItem(position);
	 
	        mViewHolder.tvTitle.setText(mList[position]);
	 
	        return convertView;
	}

	private class MyViewHolder {
        TextView tvTitle;
 
 
        public MyViewHolder(View item) {
            tvTitle = (TextView) item.findViewById(R.id.txt_test);
          ;
        }
    }
}
