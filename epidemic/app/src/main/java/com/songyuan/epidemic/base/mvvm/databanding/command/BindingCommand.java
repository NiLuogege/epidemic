package com.songyuan.epidemic.base.mvvm.databanding.command;


import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * About : kelin的ReplyCommand
 * 执行的命令回调, 用于ViewModel与xml之间的数据绑定
 */
public class BindingCommand<T> {
    private Action execute0;
    private Consumer<T> execute1;

    public BindingCommand(Action execute) {
        this.execute0 = execute;
    }

    /**
     * @param execute 带泛型参数的命令绑定
     */
    public BindingCommand(Consumer<T> execute) {
        this.execute1 = execute;
    }


    /**
     * 执行Action命令
     */
    public void execute() {
        if (execute0 != null) {
            try {
                execute0.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 执行带泛型参数的命令
     *
     * @param parameter 泛型参数
     */
    public void execute(T parameter) {
        if (execute1 != null) {
            try {
                execute1.accept(parameter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
