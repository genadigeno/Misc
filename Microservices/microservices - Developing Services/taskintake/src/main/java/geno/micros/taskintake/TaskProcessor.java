package geno.micros.taskintake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.task.launcher.TaskLaunchRequest;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@EnableBinding(Source.class)
public class TaskProcessor {

    private Source source;

    public Source getSource() {
        return source;
    }

    @Autowired
    public void setSource(Source source) {
        this.source = source;
    }

    public void publishRequest(String payload) {
        String url = "maven://geno.micros:task1:jar:0.0.1";
        //String url = "C:\\Users\\GENO\\.m2\\repository\\geno\\micros\\task1\\0.0.1";
        List<String> input = new ArrayList<String>(Arrays.asList(payload.split(",")));

        TaskLaunchRequest request = new TaskLaunchRequest(url, input, null, null, "");
        System.out.println("Created");
        GenericMessage<TaskLaunchRequest> message = new GenericMessage<TaskLaunchRequest>(request);
        source.output().send(message);
    }
}
