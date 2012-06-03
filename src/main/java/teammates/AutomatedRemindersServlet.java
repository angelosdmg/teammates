package teammates;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import teammates.persistent.Evaluation;
import teammates.persistent.Student;

@SuppressWarnings("serial")
public class AutomatedRemindersServlet extends HttpServlet {
	private final int NUMBER_OF_HOURS_BEFORE_DEADLINE = 24;
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		Evaluations evaluations = Evaluations.inst();
		Courses courses = Courses.inst();

		List<Evaluation> evaluationList = evaluations.getEvaluationList(NUMBER_OF_HOURS_BEFORE_DEADLINE);
		List<Student> studentList;
		List<Student> studentToRemindList;

		// Loop through each evaluation and send reminders to the students who
		// have
		// not
		// submitted their evaluations
		for (Evaluation e : evaluationList) {
			studentList = courses.getStudentList(e.getCourseID());
			studentToRemindList = new ArrayList<Student>();

			for (Student s : studentList) {
				if (!evaluations.isEvaluationSubmitted(e, s.getEmail())) {
					studentToRemindList.add(s);
				}
			}

			evaluations.remindStudents(studentToRemindList, e.getCourseID(),
					e.getName(), e.getDeadline());
		}

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
		doGet(req, resp);
	}
}
