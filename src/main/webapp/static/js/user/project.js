var pid = request("pid");

$(document).ready(function () {
    checkSession(function (user) {
        ProjectManager.getProject(pid, function (project) {
            if (project == null) {
                location.href = "link.html";
                return;
            }

            $("#project-panel .panel-title").text(project.name);
            document.title = project.name;

            for (var attributeName in project.attributes) {
                $("#project-attributes").mengular(".attribute-list-template", {
                    attributeName: attributeName,
                    attributeValue: project.attributes[attributeName]
                });
            }
            $("#project-attributes").mengularClearTemplate();

            $("#project-panel .panel-body").html(project.content);
        })
    });
});