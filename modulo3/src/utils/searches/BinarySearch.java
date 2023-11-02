package utils.searches;

public class BinarySearch {
    public static boolean findElementRecursive(final int[] data, final int target, int left, int right) {
        if (left > right) {
            return false;
        }

        int currentIndex = (left + right) / 2;
        if (data[currentIndex] == target) {
            return true;
        }

        if (data[currentIndex] > target) {
            return findElementRecursive(data, target, left, currentIndex - 1);
        } else {
            return findElementRecursive(data, target, currentIndex + 1, right);
        }
    }

    public static boolean findElementIterative(final int[] data, final int target) {
        int left = 0;
        int right = data.length - 1;

        while(left <= right && right >= left){
            int mid = (left + right) / 2;

            if(data[mid] == target){
                return true;
            } else if(data[mid] > target) {
                right = mid - 1;
            } else if(data[mid] < target){
                left = mid + 1;
            }
        }

        return false;
    }

}