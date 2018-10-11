package com.momo.springbootactivemq.vo;

/**
 * Created by lzm on 2018/5/19.
 */
public class OrderJobBaseVO<T> {
    private long orderId;
    private long mainOrderId;
    private int type;

    private T extra;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getMainOrderId() {
        return mainOrderId;
    }

    public void setMainOrderId(long mainOrderId) {
        this.mainOrderId = mainOrderId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public T getExtra() {
        return extra;
    }

    public void setExtra(T extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "OrderJobBaseVO{" +
                "orderId=" + orderId +
                ", mainOrderId=" + mainOrderId +
                ", type=" + type +
                ", extra=" + extra +
                '}';
    }
}
