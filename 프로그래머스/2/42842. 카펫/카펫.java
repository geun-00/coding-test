class Solution {
    public int[] solution(int brown, int yellow) {
        int half = (brown + 4) / 2;

        int low = 3;
        int high = half / 2;

        while (low <= high) {
            int height = (low + high) / 2;

            int width = half - height;

            if (width * height < brown + yellow) {
                low = height + 1;
            } else if (width * height > brown + yellow) {
                high = height - 1;
            } else {
                return new int[]{width, height};
            }
        }

        return null;
    }
}