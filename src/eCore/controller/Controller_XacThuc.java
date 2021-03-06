package eCore.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import eCore.dao.ObjectDAO;
import eCore.model.DonVi;
import eCore.model.NamHoc;
import eCore.model.TaiKhoan;
import eCore.modelDao.DAO_NamHoc;
import eCore.modelDao.DAO_TaiKhoan;
import eCore.util.Util_MD5;
import eCore.util.Util_Menu;

public class Controller_XacThuc {
	ObjectDAO<TaiKhoan> dao = new DAO_TaiKhoan();
	String maDangNhap;
	String matKhau;

	public String getMaDangNhap() {
		return maDangNhap;
	}

	public void setMaDangNhap(String maDangNhap) {
		this.maDangNhap = maDangNhap;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String dangNhap() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		ArrayList<TaiKhoan> ls = dao.listByColumns("maDangNhap", getMaDangNhap());
		TaiKhoan obj;
		if (ls.size() > 0) {
			obj = ls.get(0);
			String md5 = Util_MD5.md5(getMatKhau());
			if (obj.getMatKhau().equals(md5)) {
				session.setAttribute("maDangNhap", obj.getMaDangNhap());
				session.setAttribute("chucNangs", Util_Menu.getMenu(session.getAttribute("maDangNhap") + ""));
				return "SUCCESS";
			}
		}
		String err = "Tài khoản không tồn tại, hoặc mật khẩu không chính xác. <br/> Liên hệ bộ phận kỹ thuật khi quên mật khẩu đăng nhập. <hr/>";
		session.setAttribute("err", err);
		return "FAIL";
	}

	public String dangXuat() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.invalidate();
		return "SUCCESS";
	}
}
