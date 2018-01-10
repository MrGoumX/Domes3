import java.util.Comparator;

//Compares whatever item you give(used in CharQueueWithMinImpl for generics)
final class DefaultComparator<T extends Comparable<? super T>> implements Comparator<T> {
    public int compare(T a, T b) {
        return a.compareTo(b);
    }
}
