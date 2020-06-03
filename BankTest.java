package atm.demo4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ATM atm = new ATM("Bale", "621779136");
        atm.setPassword("997369");// 设置初始密码
        int count = 3; // 计数器（循环变量）
        while (count != 0) {// 当密码输入错误达3次，则强制退出系统
            System.out.println("请输入登录密码：");
            String registerWord = sc.next();
            if (registerWord.equals(atm.getPassword())) {// 判断两对象的内容是否相同
                atm.show();// 显示ATM的业务信息
                while (true) {
                    int choose = sc.nextInt();
                    switch (choose) {
                        case 1:
                            System.out.println("请输入存款金额：");
                            while (true) {// 设置一个死循环
                                try {// 监控有可能产生异常的代码块
                                    int money = sc.nextInt();
                                    atm.saveMoney(money);
                                    System.out.println("存款成功！");
                                    atm.show();
                                    break;
                                } catch (NotNegativeNumber e) {// 捕获异常,e就是所捕获的异常对象
                                    System.err.println("对不起，存款金额不能小于0！请重试！");// 异常处理，打印异常信息
                                } catch (UnIntegermultiplesException e) {
                                    System.out.println("对不起，存款金额必须为100的整数倍！");
                                } catch (InputMismatchException e) {// 捕获异常
                                    System.err.println("对不起，存款金额不能为字符串！请重试！");
                                    sc.nextLine();
                                }
                            }
                            break;
                        case 2:
                            System.out.println("请输入取款金额：");
                            while (true) {// 设置一个死循环
                                try {// 监控有可能产生异常的代码块
                                    int Money = sc.nextInt();
                                    atm.drawMoney(Money);
                                    System.out.println("取款成功！");
                                    atm.show();
                                    break;
                                } catch (InsufficientFundsException e) {// 捕获异常
                                    System.err.println("银行卡余额不足！请重试！");// 异常处理，打印异常信息
                                } catch (NotNegativeNumber e) {
                                    System.out.println("取款金额不能小于0！");
                                } catch (UnIntegermultiplesException e) {
                                    System.out.println("取款金额必须为100的整数倍！");
                                } catch (InputMismatchException e) {
                                    System.out.println("取款金额不能为字符串！");
                                    sc.nextLine();
                                }
                            }
                            break;
                        case 3:
                            atm.balance();// 调用方法，打印银行卡余额
                            atm.show();
                            break;
                        case 4:
                            System.out.println("请输入新密码：");
                            while (true) {
                                try {// 监控有可能产生异常的代码块
                                    String password = sc.next();
                                    atm.changePassWord(password);
                                    System.out.println("密码修改成功！");
                                    atm.show();
                                    break;
                                } catch (DigitalofLength e) {// 捕获异常
                                    System.err.println("密码长度不为6！请重试！");// 异常处理，打印异常信息
                                } catch (UnPureDigitalException e) {
                                    System.out.println("密码不能包含字符！");
                                }
                            }
                            break;
                        case 5:
                            System.out.println("\t\t开户人信息");
                            System.out.println("\t开户人姓名：" + atm.getUserName());
                            System.out.println("\t开户账  号：" + atm.getId());
                            System.out.println("\t银行卡密码：" + atm.getPassword());
                            atm.show();
                            break;
                        case 6:
                            System.out.println("请取回您的卡片！");
                            System.out.println("欢迎您再次使用XXATM服务");
                            System.exit(0);// 退出系统
                        default:
                            System.out.println("序列号输入错误！");

                    }
                }
            } else {// 当登录密码输入错误
                if ((count - 1) > 0) {
                    System.out.println("密码输入，请重试！（您还有" + (count - 1) + "次机会）");
                } else {// 当错误次数等于3时，退出系统
                    System.out.println("对不起！您的银行卡已被锁定，请前往人工服务窗口进行解绑！");
                    System.exit(0);
                }
            }
            count--;// 更新循环变量
        }
        sc.close();// 关闭输入的流，释放内存
    }
}