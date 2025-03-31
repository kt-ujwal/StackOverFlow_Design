package stackoverflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TagService {
    private final Map<String, Tag> tags;

    private static TagService tagService;
    private TagService() {
    tags = new ConcurrentHashMap<>();
    }
    public static TagService getTagService(){
        if(tagService == null){
            synchronized (TagService.class){
                if(tagService == null){
                    tagService = new TagService();
                }
            }
        }
        return tagService;
    }

    public Tag getTag(String tagId){
       return tags.get(tagId);
    }

    public List<Tag> getTags() {
        return new ArrayList<>(tags.values());
    }

    public void addTags(List<String> tagList) {
        for(String tagName : tagList){
            Tag newTag = new Tag(tagName);
            tags.put(newTag.getId(), newTag);
        }

    }
}
