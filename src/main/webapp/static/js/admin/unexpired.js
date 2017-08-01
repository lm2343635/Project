 $(document).ready(function () {

     checkAdminSession(function () {
         ProjectManager.getUnexpiredProjects(function (projects) {
             $("#project-list tbody").mengularClear();

             for (var i in projects) {
                 var project = projects[i];
                 $("#project-list tbody").mengular(".project-list-template", {
                     pid: project.pid,
                     createAt: project.createAt.format(DATE_HOUR_MINUTE_FORMAT),
                     updateAt: project.updateAt.format(DATE_HOUR_MINUTE_FORMAT),
                     expireAt: project.expireAt.format(YEAR_MONTH_DATE_FORMAT),
                     name: project.name
                 });

                 $("#" + project.pid + " .project-list-remove").click(function () {
                     var pid = $(this).mengularId();
                     var name = $("#" + pid + " .project-list-name").text();
                     $.messager.confirm("警告", "确认删除工程" + name + "吗？", function () {
                         ProjectManager.removeProject(pid, function (success) {
                             if (!success) {
                                 location.href = "session.html";
                                 return;
                             }
                             $("#" + pid).remove();
                         });
                     });
                 });
             }
         });
     });

 });