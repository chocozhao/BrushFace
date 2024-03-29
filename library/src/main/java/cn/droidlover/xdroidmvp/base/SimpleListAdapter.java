package cn.droidlover.xdroidmvp.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 *
 * ClaseName：${NAME}
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2016/12/1 10:32
 * Modified By：
 * Fixtime：2016/12/1 10:32
 * FixDescription：
 * @version
 *
 */

public abstract class SimpleListAdapter<T, H> extends XListAdapter<T> {

    public SimpleListAdapter(Context context) {
        super(context);
    }

    public SimpleListAdapter(Context context, ListItemCallback<T> callback) {
        super(context, callback);
    }

    public SimpleListAdapter(Context context, List<T> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        H holder;
        T item = data.get(position);

        if (convertView == null) {
            convertView = View.inflate(context, getLayoutId(), null);
            holder = newViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (H) convertView.getTag();
        }

        convert(holder, item, position);

        return convertView;
    }

    protected abstract H newViewHolder(View convertView);

    protected abstract int getLayoutId();

    protected abstract void convert(H holder, T item, int position);
}
