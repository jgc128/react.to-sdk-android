package to.react.sdk.android.reactto.Api.Model;


public class ApiRequestStatusResult {
    public enum StatusResult {
        Ok,
        Error
    }

    public StatusResult Status;

    public void setStatus(String status)
    {
        String titleCaseStatus = toTitleCase(status);
        Status = StatusResult.valueOf(titleCaseStatus);
    }

    // http://stackoverflow.com/a/1086134/2708478
    static String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }
}
