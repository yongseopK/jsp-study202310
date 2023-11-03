package com.jsp.chap04;

import com.jsp.entity.Dancer;
import com.jsp.repository.DancerRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


// 서블릿 == 컨트롤러
// 클라이언트의 요청을 처리하고 모델에게 로직을 위임하며
// 뷰에게 화면을 응답한다.
@WebServlet("/mvc/process")
public class ProcessServlet extends HttpServlet {

    // 데이터베이스 처리는 다른 클래스(Model)에게 위임
    // 위임을 위해서는 의존(Dependency)이 필요하다
    // 의존을 구현하는 방법은 필드로 설정하면 된다
    private DancerRepository dancerRepository = new DancerRepository();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
// form에서 넘어온 데이터 읽기 (클라이언트 데이터 처리 : controller)
        //form에서 전송한데이터 한글처리
        request.setCharacterEncoding("UTF-8");

        // DancerRegisterViewServlet에서 전달된 파라미터 읽기
        String name = request.getParameter("name");
        String crewName = request.getParameter("crewName");
        String danceLevel = request.getParameter("danceLevel");
        String[] genresArray = request.getParameterValues("genres");


        // Dancer객체를 생성, 입력값 세팅 (business logic : model(자바객체))
        // 데이터베이스에 저장 (business logic : model(자바객체)) -> 위임
        dancerRepository.save(name, crewName, danceLevel, genresArray);

        // 결과 화면에 데이터를 전달

        // 데이터를 조회해야 함
        List<Dancer> dancerList = dancerRepository.findAll();

        // jsp에게 자바 데이터를 보내는 방법 (request 객체를 이용)
        request.setAttribute("dl", dancerList); // 택배 보관함

        // 결과 화면 처리
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/chap04/dancer/list.jsp");
        rd.forward(request, resp);

    }
}
