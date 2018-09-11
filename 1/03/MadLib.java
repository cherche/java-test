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

  public String getTemplate() {
    return template;
  }

  public String getFilledStory(String[] words) {
    // If we don't cast to Object[], the compiler throws a fit
    String story = String.format(template, (Object[]) words);
    return story;
  }
}
