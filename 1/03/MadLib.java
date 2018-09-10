public class MadLib {
  private String[] descs;
  private TempPair[] template;
  private int[] indices;

  public MadLib(String[] descs, TempPair[] template) {
    this.template = template;
    this.indices = indices;
  }

  public String[] getDescs() {
    return descs;
  }

  public String getFilledStory(String[] words) {
    int i = 0;
    String story = "";

    for (; i < template.length; i++) {
      String string = template[i].string;
      int index = template[i].index;
      if (index == -1) {
        story += string;
      } else {
        String word = words[index];
        story += string + word;
      }
    }

    return story;
  }
}
