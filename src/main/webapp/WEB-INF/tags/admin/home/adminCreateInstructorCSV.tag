<%@ tag description="adminHome.jsp - Admin home create instructor account form" %>
<%@ tag import="teammates.common.util.Const" %>

<div class="well well-plain">
    <div>
        <label class="label-control">Upload CSV with Instructors</label>
        <form action="/uploadcsv" method="post" >
            <input type="file" name = "title">
            <input type="submit">
        </form>
    </div>
    <br>

    <div>
        <button class="btn btn-primary addInstructorFormControl addInstructorBtn" id="btnAddInstructor">Upload CSV</button>
    </div>
</div>