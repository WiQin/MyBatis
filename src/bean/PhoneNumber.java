package bean;

/**
 * @ClassName PhoneNumber
 * @Description
 * @Author Wangyw
 */
public class PhoneNumber {
    private String countryCode;
    private String stateCode;
    private String number;
    //100-101-102

    public PhoneNumber() {
        super();
    }

    public PhoneNumber(String str){
        if(str != null){
            String[] split = str.split("-");
            this.countryCode = split[0];
            this.stateCode = split[1];
            this.number = split[2];
        }
    }

    public PhoneNumber(String countryCode, String stateCode, String number) {
        this.countryCode = countryCode;
        this.stateCode = stateCode;
        this.number = number;
    }

    //将对象中保存的数据转换为字符串
    public String getAsString(){
        return countryCode+"-"+stateCode+"-"+number;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "countryCode='" + countryCode + '\'' +
                ", stateCode='" + stateCode + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
