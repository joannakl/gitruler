package gitruler;

import java.util.Map;

public class Rule {

    /***
     * Adding a new rule:
     * Add a test to the test repo
     * add the title of the rule in getTitle in Rule.
     * Add the checkrule in the GitInterator
     */

    Map<String, Object> details;

    Rule(Map<String, Object> details){
        this.details = details;
    }

    String getTitle() {

        if (details.containsKey("alternative-title")){
            return getStringParameter("alternative-title");
        }

        switch (getRuleName()){
            case "file-contains-in-head":
                return "The file: " + (String) details.get("path") + " contains the text '" + (String) details.get("contents") + "'";
            case "file-tracked-in-head":
                return "The file is tracked: " + (String) details.get("path");
            case "head-exists":
                return "There is a valid repository";
            case "file-untracked-in-head":
                return "The file is not tracked: " + (String) details.get("path");
            case "file-has-hash-in-head":
                return "An existing file should now be at: " + (String) details.get("path");
            case "last-commit-message-for-file-contains":
                return "Latest commit message for " + (String) details.get("path") + " contains " + (String) details.get("contents");
            case "any-commit-message-for-file-contains":
                return "Any commit message for " + (String) details.get("path") + " contains " + (String) details.get("contents");
            default:
                return "Unknown rule";
        }
    }

    String getStringParameter(String key){
        // todo: Check that the parameter exists and is the right type
        return (String) details.get(key);
    }

    String getRuleName(){
        return (String) details.get("rule");
    }

    boolean stopOnFail(){
        return details.containsKey("stop-on-fail") && ((Boolean)details.get("stop-on-fail"));
    }

    boolean hasPreText() {
        return details.containsKey("pre-text");
    }

    boolean hasPostText() {
        return details.containsKey("post-text");
    }

    String getPreText() {
        return getStringParameter("pre-text");
    }

    String getPostText() {
        return getStringParameter("post-text");
    }

    boolean getBooleanParameter(String s) {
        return details.containsKey(s) && (boolean) details.get(s);
    }
}
