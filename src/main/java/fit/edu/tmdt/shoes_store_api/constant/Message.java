package fit.edu.tmdt.shoes_store_api.constant;

public interface Message {
   String CODE_EXIST = "Mã đã tồn tại";
   String ACCOUNT_EXIST = "Tài khoản đã tồn tại";
   String ACCOUNT_NOT_EXIST = "Tài khoản không tồn tại";
   String ACCOUNT_NOT_VERIFY = "Tài khoản chưa xác thực";
   String ACCOUNT_LOCKED = "Tài khoản đã bị khóa";
   String ACCOUNT_WRONG_PASSWORD = "Sai mật khẩu";
   String LOGIN_SUCCESS = "Đăng nhập thành công";
   String LOGIN_FAILED = "Đăng nhập thất bại";


   public  String EMAIL_SUBJECT_ORDER = App.APP + " - Đặt hàng";
   public  String EMAIL_SUBJECT_REGISTER =  App.APP  + " - Đăng ký tài khoản";
   public  String EMAIL_SUBJECT_RESET_PASSWORD =  App.APP + " - Cập nhật tài khoản?";
   public  String EMAIL_LINK_OTP = App.CLIENT_HOST + "/otp/";
}
