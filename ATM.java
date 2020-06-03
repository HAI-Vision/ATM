package atm.demo4;

public class ATM {
    private String userName;// 用户名
    private String id; // 银行卡号
    private String password;// 银行卡密码
    private int money;// 银行卡余额

    /** 构造方法 */
    public ATM(String userName, String id) {
        this.userName = userName;
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 显示ATM的各种操作
     */
    public void show() {
        System.out.println("-------------欢迎" + userName + "使用XXATM服务-------------");
        System.out.println("\t\t1、存款业务");
        System.out.println("\t\t2、取款业务");
        System.out.println("\t\t3、查询余额业务");
        System.out.println("\t\t4、修改密码");
        System.out.println("\t\t5、查看账户信息");
        System.out.println("\t\t6、退出");
        System.out.println("-------------请选择相应的序列号！--------------");

    }

    /**
     * 存钱原则：在ATM上存钱的金额必须是100的整数倍
     * 
     * @param money 存钱的金额
     * @throws NotNegativeNumber           输入的存款金额小于0的异常处理
     * @throws UnIntegermultiplesExcepyion 输入的存款金额不是100的整数倍的异常处理
     */
    public void saveMoney(int money) throws NotNegativeNumber, UnIntegermultiplesException {// 谁抛出，谁处理
        if (money < 0) {// 存款金额小于0
            throw new NotNegativeNumber(); // 手动抛出异常
        } else if (this.money >= 0 && money % 100 == 0) {// 判断银行卡余额是否大于0以及存款金额是否为100的整数倍
            this.money = this.money + money;
        } else if (this.money >= 0 && money % 100 != 0) {// 当取款金额不是100的整数倍
            throw new UnIntegermultiplesException();// 手动抛出异常
        }
    }

    /**
     * 取款原则：在ATM上取款的金额必须不大于银行卡余额，此外取款的金额必须是100的整数倍
     * 
     * @param Money 取款金额
     * @throws UnIntegermultiplesException 取款金额不是100的整数倍的异常处理
     * @throws InsufficientFundsException  取款金额大于余额的异常处理
     * @throws NotNegativeNumber           取款金额小于0的异常处理
     */
    public void drawMoney(int Money) throws UnIntegermultiplesException, InsufficientFundsException, NotNegativeNumber {// 谁抛出，谁处理
        if (Money > money) {
            throw new InsufficientFundsException();// 手动抛出异常
        } else if (Money <= money && Money % 100 == 0 && Money >= 0) {
            money = money - Money;// 更新银行余额
        } else if (Money <= money && Money % 100 != 0 && Money >= 0) {// 当取款金额不是100的整数倍时
            throw new UnIntegermultiplesException();// 手动抛出异常
        } else if (Money < 0) {
            throw new NotNegativeNumber();
        }
    }

    /**
     * 余额查询
     * 
     * @param money
     */
    public void balance() {
        System.out.println("您的银行卡余额为：" + money);
    }

    /**
     * 修改密码
     * 
     * @param password 原密码
     * @throws DigitalofLength        密码长度不为6的异常处理
     * @throws UnPureDigitalException 密码不是纯数字的异常处理
     */
    public void changePassWord(String password) throws DigitalofLength, UnPureDigitalException {// 谁抛出，谁处理
        for (int i = 0; i < password.length(); i++) {
            if (!Character.isDigit(password.charAt(i))) { // 遍历字符串，判断是否为纯数字
                throw new UnPureDigitalException();// 手动抛出异常
            } else if (password.length() > 6 || password.length() < 6) {// 判断密码长度是否为大于（小于）6
                throw new DigitalofLength(); // 手动抛出异常
            } else if (password.length() == 6) { // 判断密码长度是否为6
                setPassword(password); // 更新密码
            }
        }

    }
}