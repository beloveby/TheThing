package dachuangchuang.group.thething;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 边园 on 2016/2/4.
 */
public class TabsAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;

    public TabsAdapter(Context context, List<String> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(this.context);
        textView.setText(this.list.get(position));
        return textView;
    }
}
