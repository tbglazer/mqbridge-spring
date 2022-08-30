package il.co.fibi.comm.mqbridge.utils;

public class MetricsUtils {

//    @Inject MetricRegistry registry;
//
//    public Context start(Counted counted, Tag tag) {
//        List<Tag> tags = tagsFromStringArray(counted.tags());
//        tags.add(tag);
//        Timer timer = registry.timer(counted.name(), tags.toArray(new Tag[0]));
//        return timer.time();
//    }
//
//    public Context start(String name, Tag... tags) {
//        Timer timer = registry.timer(name, tags);
//        return timer.time();
//    }
//
//    public void stop(Context context) {
//        context.stop();
//    }
//
//    public List<Tag> tagsFromStringArray(String[] tags) {
//        return Arrays.stream(tags).map(t->new Tag(t.split("=")[0],t.split("=")[1])).collect(Collectors.toList());
//    }
}