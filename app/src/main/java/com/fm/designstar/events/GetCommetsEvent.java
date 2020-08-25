package com.fm.designstar.events;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/25 16:13
 * @update : 2018/9/25
 */
public class GetCommetsEvent extends BaseEvent {

   private int num;
   private String id;

    public GetCommetsEvent(String id, int num) {
        this.id=id;

        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getName() {
        return num;
    }

    public void setName(int num) {
        this.num = num;
    }
}
