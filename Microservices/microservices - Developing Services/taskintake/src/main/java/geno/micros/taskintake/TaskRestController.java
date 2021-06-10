package geno.micros.taskintake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskRestController {

    private TaskProcessor taskProcessor;

    public TaskProcessor getTaskProcessor() {
        return taskProcessor;
    }

    @Autowired
    public void setTaskProcessor(TaskProcessor taskProcessor) {
        this.taskProcessor = taskProcessor;
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    public @ResponseBody String launchTask(@RequestBody String body) {
        taskProcessor.publishRequest(body);
        System.out.println("Request Made");
        return "success";
    }
}
