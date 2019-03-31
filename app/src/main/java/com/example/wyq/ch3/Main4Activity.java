package com.example.wyq.ch3;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends AppCompatActivity {

    ListView listView;
    List<String> datas = new ArrayList<String>();
    ListViewAdapter adapter;

    class ListViewAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder viewHolder;
            if(convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(Main4Activity.this, R.layout.list_items2, null);
                viewHolder.text = (TextView)convertView.findViewById(R.id.header);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder)convertView.getTag();
            }
            viewHolder.text.setText(datas.get(position));
            //判断position位置是否被选中，改变颜色
            if(listView.isItemChecked(position)) {
                convertView.setBackgroundColor(0xFF670003);
            } else {
                convertView.setBackgroundColor(0xFFFFFFFF);
            }
            return convertView;
        }

        class ViewHolder {
            TextView text;
        }
    }

    public void initData() {
        datas.add("One");
        datas.add("Two");
        datas.add("Three");
        datas.add("Four");
        datas.add("Five");
    }

    private ActionMode mActionMode;
    private ActionMode.Callback mCallBack = new ActionMode.Callback() {
        @Override
        public boolean onPrepareActionMode(ActionMode arg0, Menu arg1) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode arg0) {
            listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

            mActionMode = null;
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.actionmode, menu);
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            Log.d("click", "onActionItemClicked");
//                switch (item.getItemId()) {
//                    case R.id.action_cancel:
//                        Toast.makeText(MainActivity.this, "cancel", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.action_test:
//                        Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.action_settings:
//                         Toast.makeText(MainActivity.this, "setting", Toast.LENGTH_SHORT).show();
//                         break;
//                }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        listView = (ListView)findViewById(R.id.mylist);
        initData();
        adapter = new ListViewAdapter();
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                adapter.notifyDataSetChanged();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                // TODO Auto-generated method stub
                mActionMode = startActionMode(mCallBack);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        // TODO Auto-generated method stub
                        //点击item后，通知adapter重新加载view
                        adapter.notifyDataSetChanged();
                    }
                });
                return true;
            }
        });


    }
}
