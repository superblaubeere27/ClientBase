package net.superblaubeere27.clientbase.gui.tabgui;

public class SubTab<T> {
    private String text;
    private TabActionListener<T> listener;
    private T object;

    public SubTab(String text, TabActionListener<T> listener, T object) {
        this.text = text;
        this.listener = listener;
        this.object = object;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TabActionListener<T> getListener() {
        return listener;
    }

    public void setListener(TabActionListener<T> listener) {
        this.listener = listener;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public void press() {
        if (listener != null) {
            listener.onClick(this);
        }
    }

    public interface TabActionListener<T> {
        void onClick(SubTab<T> tab);
    }
}
