## Android ListView的用法
**activity_main.xml**中添加ListView组件
```
    <ListView
        android:id="@+id/mylist"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:listSelector="#670003"
        >
    </ListView>
```

**list_items.xml**
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="horizontal"
    android:padding="10sp"
    >

    <TextView
        android:id="@+id/header"
        android:layout_width="wrap_content"
        android:layout_height="40dp"
        android:gravity="center_vertical"
        android:text="20sp"
        android:textColor="#000"
        android:layout_weight="100"
        android:textSize="16sp" />

    <ImageView
        android:id="@+id/images"
        android:layout_width="40dp"
        android:maxWidth="40dp"
        android:layout_height="40dp"
        android:scaleType="centerCrop"
        android:layout_weight="1"/>
</LinearLayout>

```
**MainActivity**

```
public class MainActivity extends AppCompatActivity {

    //用于显示布局里的动物名称
    private String[] names = new String[]{"Lion","Tiger","Monkey","Dog","Cat","elephant"};
    private int[] image=new int[]{R.mipmap.lion,R.mipmap.tiger,R.mipmap.monkey,R.mipmap.dog,R.mipmap.cat,R.mipmap.elephant};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final int color1 = 0xFF670003;
        final int color2 = 0xFFFFFFFF;
        //创建一个list集合，list集合的元素是Map

        List<Map<String, Object>> ListItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("header", names[i]);
            listItem.put("images", image[i]);
            //加入list集合
            ListItems.add(listItem);
        }
        //创建一个SimpleAdapter，此处严格按照定义数组names与image顺序,否则会出现程序build成功却运行失败且难以解决错误
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, ListItems, R.layout.list_items, new String[]{"header", "images"}, new int[]{R.id.header, R.id.images});

        //为ListView设置Adapter
        final ListView list = (ListView) findViewById(R.id.mylist);
        list.setAdapter(simpleAdapter);
		//对应点击事件
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                int flag=0;
                System.out.println(names[position]+position+"被单击");
                //点击则改变状态，改变颜色
                switch (flag){
                    case 0:
                        //此处对应上面布局文件的点击函数
                        CharSequence text=names[position];
                        //定义一个Toast表示哪一个图片所在item被点击
                        int duration= Toast.LENGTH_SHORT;
                        Toast toast=Toast .makeText(MainActivity.this,text,duration);
                        toast.show();
                        flag=1;
                        break;
                }
            }
        });
    }
 }
```
效果图：
![在这里插入图片描述](https://raw.githubusercontent.com/w840980261/UI-components/master/img/1.png)
## 创建自定义布局的AlertDialog
**dialog.xml**

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="horizontal"
    android:visibility="visible">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="197dp"
        android:orientation="vertical">

        <ImageView
            android:id="@+id/imageView"
            android:layout_width="match_parent"
            android:layout_height="197dp"
            android:layout_weight="1"
            android:scaleType="fitXY"
            app:srcCompat="@mipmap/header_logo" />
        <!---->

        <EditText
            android:id="@+id/Username"
            android:layout_width="match_parent"
            android:layout_height="197dp"
            android:layout_weight="1"
            android:inputType="textPersonName"
            android:hint="Username"/>

        <EditText
            android:id="@+id/Password"
            android:layout_width="match_parent"
            android:layout_height="197dp"
            android:layout_weight="1"
            android:hint="Password"
            android:password="true"/>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="197dp"
            android:layout_weight="1"
            android:orientation="horizontal"
            android:layout_marginTop="5dp"
            android:background="#eee"
            android:paddingTop="2dp"
            >

            <Button
                android:id="@+id/dialog_btn_cancel"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:background="#fff"
                android:text="Cancel"
                android:textAllCaps="false"
                android:layout_marginRight="1dp"
                />

            <Button
                android:id="@+id/dialog_btn_signIn"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:background="#fff"
                android:text="Sign in"
                android:textAllCaps="false"
                android:layout_marginLeft="1dp"/>
        </LinearLayout>
    </LinearLayout>

</LinearLayout>


```

**Main2Activity**

```
public class Main2Activity extends AppCompatActivity {
    private void showLayoutDialog() {
        //加载布局并初始化组件
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog,null);
        Button dialogBtnSignIn = (Button) dialogView.findViewById(R.id.dialog_btn_signIn);
        Button dialogBtnCancel = (Button) dialogView.findViewById(R.id.dialog_btn_cancel);

        final AlertDialog.Builder layoutDialog = new AlertDialog.Builder(this);
        layoutDialog.setView(dialogView);

        //设置组件
        dialogBtnSignIn .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        dialogBtnCancel .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        layoutDialog.create().show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        showLayoutDialog();
    }
}
```
效果图
![在这里插入图片描述](https://raw.githubusercontent.com/w840980261/UI-components/master/img/2.png)
## 使用XML定义菜单

**menu.xml**

```
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:title="字体大小">
        <menu>
            <!--定义一组选项菜单-->
            <group>
                <!--定义多个菜单项-->
                <item
                    android:id="@+id/font_10"
                    android:title="10号"/>
                <item
                    android:id="@+id/font_16"
                    android:title="16号"/>
                <item
                    android:id="@+id/font_20"
                    android:title="20号"/>
            </group>
        </menu>
    </item>
    <!--定义一个普通菜单项-->
    <item android:id="@+id/plain_item"
        android:title="普通菜单项"/>
    <item android:title="字体颜色">
        <menu>
            <!--定义一个普通选项菜单-->
            <group>
                <!--定义三个菜单项-->
                <item
                    android:id="@+id/red_font"
                    android:title="红色"/>
                <item
                    android:id="@+id/black_font"
                    android:title="黑色"/>
            </group>
        </menu>
    </item>
</menu>
```
**Main3Activity**

```
public class Main3Activity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        textView = (TextView) findViewById(R.id.editText);
        // 为文本框注册上下文菜单
        registerForContextMenu(textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        //装填R.Menu.my_menu菜单，并添加到menu中
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.isCheckable()){
            //勾选菜单项
            item.setCheckable(true);
        }
        //switch 判断单击哪个菜单项，并有针对性的做出响应
        switch (item.getItemId()){
            case R.id.font_10:
                textView.setTextSize(10 * 2);
                break;
            case R.id.font_16:
                textView.setTextSize(16 * 2);
                break;
            case R.id.font_20:
                textView.setTextSize(20 * 2);
                break;
            case R.id.plain_item:
                Toast.makeText(getApplicationContext(), "普通菜单项", Toast.LENGTH_SHORT).show();
                break;
            case R.id.red_font:
                textView.setTextColor(0xFFFF0000);
                break;
            case R.id.black_font:
                textView.setTextColor(0xFF000000);
                break;
        }
        return true;
    }
}
```
效果图
![菜单](https://raw.githubusercontent.com/w840980261/UI-components/master/img/3_1.png)
![选择字体](https://raw.githubusercontent.com/w840980261/UI-components/master/img/3_2.png)
![普通菜单项点击Toast提示](https://raw.githubusercontent.com/w840980261/UI-components/master/img/3_3.png)
![更改字体颜色](https://raw.githubusercontent.com/w840980261/UI-components/master/img/3_4.png)
![字体变红](https://raw.githubusercontent.com/w840980261/UI-components/master/img/3_5.png)
## 创建上下文操作模式(ActionMode)的上下文菜单
**activity_main4.xml**

```
<ListView
    android:id="@+id/mylist"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:listSelector="#670003"
    >
</ListView>
```
**list_items2.xml**

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="horizontal"
    android:padding="10sp"
    >
    <ImageView
        android:id="@+id/images"
        android:layout_width="40dp"
        android:maxWidth="40dp"
        android:layout_height="40dp"
        android:scaleType="centerCrop"
        android:src="@mipmap/ic_launcher_round" />
    <TextView
        android:id="@+id/header"
        android:layout_width="wrap_content"
        android:layout_height="40dp"
        android:paddingLeft="10dp"
        android:gravity="center_vertical"
        android:textColor="#000"
        android:layout_weight="100"
        android:textSize="16sp" />
</LinearLayout>

```
**actionmode.xml**

```
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/delete"
        android:icon="@android:drawable/ic_delete"
        android:orderInCategory="100"
        android:showAsAction="ifRoom|withText"
        android:title="@string/app_name" />
</menu>
```
**Main4Activity**

```
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
```
