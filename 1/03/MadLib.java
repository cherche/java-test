public class MadLib {
  private String[] descs;
  private String[] template;
  private int[] indices;

  public MadLib(String[] descs, String[] template, int[] indices) {
    this.template = template;
    this.indices = indices;
  }

  public String[] getDescs() {
    return descs;
  }

  public String getFilledStory(String[] words) {
    int i = 0;
    String story = "";

    for (; i < indices.length; i++) {
      int index = indices[i];
      story += template[i] + words[index];
    }

    // If there's more template left over, just print it all out
    // This just makes the MadLib class easier to use
    if (template.length > indices.length) {
      for (; i < template.length; i++) {
        story += template[i];
      }
    }

    return story;
  }
}
