package testClone.test;

import java.util.ArrayList;
import java.util.List;

public class Event {
    private List<Callback> callbacks;
    public Event() {
        callbacks = new ArrayList<>();
    }
    public void addCallback(Callback callback) {
        callbacks.add(callback);
    }
    public void fireEvent(Object data) {
        for (Callback callback : callbacks) {
            callback.execute(data);
        }
    }
    public interface Callback {
        void execute(Object data);
    }
}

 class ExampleUsage {
    public static void main(String[] args) {
        // 创建事件
        Event event = new Event();
        // 添加回调函数
        event.addCallback(data -> System.out.println("回调函数被调用并处理数据：" + data));
        // 触发事件并传递数据
        event.fireEvent("这是传递给回调函数的数据");
    }
}
