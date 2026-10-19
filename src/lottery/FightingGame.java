package lottery;

import java.util.Random;

/**
 * @author bnhh
 * @Description
 * @date 2026/10/19-19:38
 */
public class FightingGame {
    private String name;
    private int blood;
    private char gender;
    private String face;

    String[] boyFaces = {"handsome", "ugly1", "justsoso1"};
    String[] girlFaces = {"beautiful", "ugly2", "justsoso2"};
    String[] attack_desc = {"%s攻击了%s", "%s击打了%s", "%s回旋踢了%s", "%s上勾拳了%s","%s下勾拳了%s"};
    String[] injure_desc = {"%s受了皮外伤", "%s流血了", "%s骨折了", "%s倒下了"};

    public FightingGame() {
    }

    public FightingGame(String name, int blood, char gender) {
        this.name = name;
        this.blood = blood;
        this.gender = gender;
        this.face = setFace(gender);

    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return blood
     */
    public int getBlood() {
        return blood;
    }

    /**
     * 设置
     * @param blood
     */
    public void setBlood(int blood) {
        this.blood = blood;
    }

    /**
     * 获取
     * @return gender
     */
    public char getGender() {
        return gender;
    }

    /**
     * 设置
     * @param gender
     */
    public void setGender(char gender) {
        this.gender = gender;
    }

    /**
     * 获取
     * @return face
     */
    public String getFace() {
        return face;
    }

    /**
     * 设置
     * @param
     */
    public String setFace(char gender) {
        Random r = new Random();
        if (gender == '男') {
            int index = r.nextInt(boyFaces.length);
            return boyFaces[index];
        } else if (gender == '女') {
            int index = r.nextInt(girlFaces.length);
            return girlFaces[index];
        }else {
            return "unknow";
        }
    }



    /**
     * 设置
     * @param attack_desc
     */
    public void setAttack_desc(String[] attack_desc) {
        this.attack_desc = attack_desc;
    }

    /**
     * 获取
     * @return injure_desc
     */
    public String[] getInjure_desc() {
        return injure_desc;
    }

    /**
     * 设置
     * @param injure_desc
     */
    public void setInjure_desc(String[] injure_desc) {
        this.injure_desc = injure_desc;
    }

    //攻击行为
    public void attack(FightingGame role){
        //攻击的描述
        Random r = new Random();
        int loseBlood=r.nextInt(20)+1;
        int remainBlood=role.getBlood()-loseBlood;
        if(remainBlood<0){
            role.setBlood(0);
        }else {
            role.setBlood(remainBlood);
        }
        int index1 = r.nextInt(attack_desc.length);
        String s1 = attack_desc[index1];
        System.out.printf(s1,this.getName(),role.getName()+","+role.getName()+"剩余血量："+role.getBlood());
        System.out.println();

        //受伤的描述
        if(role.getBlood()>=80){
            System.out.printf(injure_desc[0],role.getName());
        } else if (role.getBlood()>=60&&role.getBlood()<80) {
            System.out.printf(injure_desc[1],role.getName());
        } else if (role.getBlood()>=40&&role.getBlood()<60) {
            System.out.printf(injure_desc[2],role.getName());
        }else if (role.getBlood()<40) {
            System.out.printf(injure_desc[3],role.getName());
        }
        System.out.println();

    }


    public void showInfo(){
        System.out.println(this.getName());
        System.out.println(this.getBlood());
        System.out.println(this.getFace());
        System.out.println(this.getGender());
    }
}
