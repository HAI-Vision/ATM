package atm.demo4;

/**
 * 取款金额大于余额的异常处理
 */
public class InsufficientFundsException extends Exception {// 自定义异常
    public InsufficientFundsException() {
        super();
    }
}