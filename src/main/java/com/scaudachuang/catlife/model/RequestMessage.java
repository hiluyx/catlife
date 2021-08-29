package com.scaudachuang.catlife.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class RequestMessage<M> {
    private int status;
    private String errMsg;
    private M data;
    public RequestMessage() {}
    public static <M> RequestMessage<M> OK(M data) {
        RequestMessage<M> msg = new RequestMessage<>();
        msg.status = 0;
        msg.errMsg = "OK";
        msg.data = data;
        return msg;
    }

    public static <M> RequestMessage<M> ERROR(int status, String errMsg, M data) {
        RequestMessage<M> msg = new RequestMessage<>();
        msg.status = status;
        msg.errMsg = errMsg;
        msg.data = data;
        return msg;
    }

    public static <M> RequestMessage<M> INSERT_BOOL(boolean insert, M m) {
        RequestMessage<M> msg = new RequestMessage<>();
        msg.status = insert ? 0 : 15600;
        msg.data = m;
        return msg;
    }
}
