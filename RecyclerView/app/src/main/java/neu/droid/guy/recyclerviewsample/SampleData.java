package neu.droid.guy.recyclerviewsample;

public class SampleData {

    private String mainText;
    private String leftText;
    private String rightText;


    public SampleData(String mainTextData, String leftTextData, String rightTextData) {
        this.mainText = mainTextData;
        this.leftText = leftTextData;
        this.rightText = rightTextData;
    }

    public String getRightText() {
        return rightText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
    }

    public String getLeftText() {
        return leftText;
    }

    public void setLeftText(String leftText) {
        this.leftText = leftText;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }
}
