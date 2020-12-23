package jp.co.internous.lion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.internous.lion.model.domain.MstUser;
import jp.co.internous.lion.model.mapper.MstUserMapper;
import jp.co.internous.lion.model.session.LoginSession;

@Controller

@RequestMapping("/lion/mypage")

public class MyPageController {
	
	@Autowired private MstUserMapper userMapper;
	
	@Autowired private LoginSession loginSession;
	
	//「マイページ画面/初期表示」
	@RequestMapping("/")
	public String index(Model m) {
		
			//ユーザー名とパスワードがＤＢに存在しているか検索
			MstUser user = userMapper.findByUserNameAndPassword(loginSession.getUserName(), loginSession.getPassword());
			
			//上記結果をマイページに渡す
			m.addAttribute("user", user);
			// page_header.htmlでsessionの変数を表示させているため、loginSessionを画面に送る。
			m.addAttribute("loginSession", loginSession);
			
			//my_page.htmlを表示
			return "my_page";
			
	}

}
