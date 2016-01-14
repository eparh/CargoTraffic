package models;

/**
 * Created by Anton Chernov on 1/2/2016.
 */
public class Reply<T> {
    public ReplyStatus status;
    public T data;

    public Reply() {
        this.status = ReplyStatus.UNAUTHENTICATED;
    }

    public Reply(ReplyStatus status, T data) {
        this.status = status;
        this.data = data;
    }
}