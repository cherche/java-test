public class MadLib {
  private String[] descs;
  private String template;

  public MadLib(String[] descs, String template) {
    this.descs = descs;
    this.template = template;
  }

  public String[] getDescs() {
    return descs;
  }

  public String getFilledStory(String[] words) {
    String story = String.format(template, (Object[]) words);
    return story;
  }
}
