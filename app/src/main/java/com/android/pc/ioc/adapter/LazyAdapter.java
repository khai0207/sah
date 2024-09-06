package com.android.pc.ioc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.android.pc.ioc.app.Ioc;
import com.android.pc.ioc.image.ImageDownloader;
import com.android.pc.ioc.image.Utils;
import com.android.pc.ioc.inject.InjectBinder;
import com.android.pc.ioc.inject.InjectView;
import com.android.pc.ioc.view.listener.OnListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes.dex */
public class LazyAdapter<T1, T2> extends BaseAdapter {
    private Class<?> clazz;
    private Constructor<?> constructor;
    private Context context;
    private ArrayList<T1> dataList;
    ImageDownloader imageDownloader;
    private LayoutInflater layoutInflater;
    private int layout_id;
    private String packageName;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    public LazyAdapter(ListView listView, ArrayList<T1> arrayList, int i) {
        this.layout_id = -1;
        this.imageDownloader = null;
        this.dataList = arrayList;
        this.layout_id = i;
        Context context = listView.getContext();
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.packageName = Ioc.getIoc().getApplication().getPackageName();
        this.imageDownloader = new ImageDownloader(this.context, 0);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.android.pc.ioc.adapter.LazyAdapter.1
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i2) {
                if (i2 == 2) {
                    if (Utils.hasHoneycomb()) {
                        return;
                    }
                    LazyAdapter.this.imageDownloader.setPauseWork(true);
                    return;
                }
                LazyAdapter.this.imageDownloader.setPauseWork(false);
            }
        });
        try {
            Constructor<?>[] constructors = ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]).getConstructors();
            if (constructors.length > 0) {
                this.constructor = constructors[0];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ImageDownloader getImageDownloader() {
        return this.imageDownloader;
    }

    public void setImageDownloader(ImageDownloader imageDownloader) {
        this.imageDownloader = imageDownloader;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        this.clazz = this.dataList.size() > 0 ? this.dataList.get(0).getClass() : null;
        return this.dataList.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.dataList.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        Object tag;
        try {
            if (view == null) {
                view = this.layoutInflater.inflate(this.layout_id, (ViewGroup) null);
                try {
                    tag = this.constructor.newInstance(this);
                } catch (Exception unused) {
                    tag = this.constructor.newInstance(this.context);
                }
                setView(tag, view, i);
                view.setTag(tag);
            } else {
                tag = view.getTag();
            }
            deal(this.dataList.get(i), tag, i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    public void deal(T1 t1, T2 t2, int i) {
        injectAdapter(t2, i);
    }

    public void injectAdapter(Object obj, int i) {
        String string;
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        if (declaredFields == null || declaredFields.length <= 0) {
            return;
        }
        for (Field field : declaredFields) {
            if (((InjectView) field.getAnnotation(InjectView.class)) != null && (string = getString(i, field.getName())) != null) {
                try {
                    field.setAccessible(true);
                    Object obj2 = field.get(obj);
                    if (obj2 != null && View.class.isAssignableFrom(obj2.getClass())) {
                        ((View) obj2).setTag(Integer.valueOf(i));
                        if (TextView.class.isAssignableFrom(obj2.getClass())) {
                            ((TextView) obj2).setText(string);
                        }
                        if (ImageView.class.isAssignableFrom(obj2.getClass())) {
                            download((ImageView) obj2, string);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setView(Object obj, View view, int i) {
        View findViewById;
        try {
            Field[] declaredFields = obj.getClass().getDeclaredFields();
            if (declaredFields == null || declaredFields.length <= 0) {
                return;
            }
            for (Field field : declaredFields) {
                field.setAccessible(true);
                InjectView injectView = (InjectView) field.getAnnotation(InjectView.class);
                if (injectView != null) {
                    if (injectView.value() != -1) {
                        findViewById = view.findViewById(injectView.value());
                    } else {
                        findViewById = view.findViewById(Ioc.getIoc().getApplication().getResources().getIdentifier(field.getName(), "id", this.packageName));
                    }
                    if (findViewById == null) {
                        Ioc.getIoc().getLogger().e("变量  " + field + "  无法赋值，请检查ID和NAME");
                    } else {
                        try {
                            if (View.class.isAssignableFrom(findViewById.getClass())) {
                                field.set(obj, findViewById);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        InjectBinder[] binders = injectView.binders();
                        if (binders != null) {
                            for (InjectBinder injectBinder : binders) {
                                for (Class<? extends OnListener> cls : injectBinder.listeners()) {
                                    try {
                                        cls.newInstance().listener(findViewById, obj, injectBinder.method());
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void download(ImageView imageView, String str) {
        this.imageDownloader.loadImage(str, imageView);
    }

    private String getString(int i, String str) {
        T1 t1 = this.dataList.get(i);
        if (Map.class.isAssignableFrom(this.clazz)) {
            Map map = (Map) t1;
            return map.containsKey(str) ? map.get(str).toString() : "";
        }
        try {
            return this.clazz.getDeclaredMethod("get" + Character.toUpperCase(str.charAt(0)) + str.substring(1), new Class[0]).invoke(t1, new Object[0]).toString();
        } catch (Exception unused) {
            return null;
        }
    }
}
