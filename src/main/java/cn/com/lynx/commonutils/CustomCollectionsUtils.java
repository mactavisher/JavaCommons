package cn.com.lynx.commonutils;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 集合操作类
 *
 * 不同与java.util.Collections 扩展了一些排序的方法和二分算发(WIP)
 *
 * 与之还有一些集合的聚合操作
 *
 * @author Lynx
 * @see java.util.Collections
 * @since 2019/8/22 10:20
 */
@SuppressWarnings("unused")
public final class CustomCollectionsUtils {

    /*make non-instantiation ability*/
    private CustomCollectionsUtils() {}

    /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*
     *                                sorting misc                                       *
     *+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    /**
     * 冒泡排序 针对自定义排序方法， 自定义实现了Comparator的比较器对集合元素经行排序
     *
     * @param list
     *            待排序的集合
     * @param comparator
     *            比较器
     * @param <E>
     *            数据类型
     */
    public static <E> void bubbleSort(List<E> list, Comparator<? super E> comparator) {
        if (null == list || list.isEmpty()) {
            return;
        }
        E temp;
        final int size = list.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * 冒泡排序 针对自定义排序方法， 自定义实现了Comparator的比较器对集合元素经行排序
     *
     * @param list
     *            待排序的集合
     * @param <E>
     *            数据类型
     */
    public static <E extends Comparable<? super E>> void bubbleSort(List<E> list) {
        if (null == list || list.isEmpty()) {
            return;
        }
        E temp;
        final int size = list.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * 选择排序 针对实现了Comparable的接口的实体进行排序
     *
     * @param list
     *            待排序的集合
     * @param <E>
     *            数据类型
     */
    public static <E extends Comparable<? super E>> void selectSort(List<E> list) {
        if (null == list || list.isEmpty()) {
            return;
        }
        E temp;
        final int size = list.size();
        for (int i = 0; i < size; i++) {
            // 记录每一次循环最小值的位置
            int pos = i;
            for (int j = i + 1; j < size; j++) {
                if (list.get(pos).compareTo(list.get(j)) > 0) {
                    pos = j;
                }
            }
            // 最小的数与第i个位置的数交换
            temp = list.get(i);
            list.set(i, list.get(pos));
            list.set(pos, temp);
        }
    }

    /**
     * 选择排序 自定义的排序对象对集合元素经行排序
     *
     * @param list
     *            待排序的集合
     * @param comparator
     *            比较器
     * @param <E>
     *            数据类型
     */
    public static <E> void selectSort(List<E> list, Comparator<? super E> comparator) {
        if (null == list || list.isEmpty()) {
            return;
        }
        E temp;
        final int size = list.size();
        for (int i = 0; i < size; i++) {
            // 记录每一次循环最小值的位置
            int pos = i;
            for (int j = i + 1; j < size; j++) {
                if (comparator.compare(list.get(pos), list.get(j)) > 0) {
                    pos = j;
                }
            }
            // 最小的数与第i个位置的数交换
            temp = list.get(i);
            list.set(i, list.get(pos));
            list.set(pos, temp);
        }
    }

    /**
     * 插入排序 针对实现了Comparable的接口的实体进行排序
     *
     * @param list
     *            待排序的集合
     * @param <E>
     *            数据类型
     */
    public static <E extends Comparable<? super E>> void insertSort(List<E> list) {
        if (null == list || list.isEmpty()) {
            return;
        }
        E temp;
        final int size = list.size();
        for (int i = 1; i < size; i++) {
            temp = list.get(i);
            for (int j = 0; j < i; j++) {
                if (temp.compareTo(list.get(j)) < 0) {
                    for (int k = i; k > j; k--) {
                        list.set(k, list.get(k - 1));
                    }
                    list.set(j, temp);
                    break;
                }
            }
        }
    }

    /**
     * 插入排序
     *
     * @param list
     *            待排序的集合
     * @param comparator
     *            比较器
     * @param <E>
     *            数据类型
     */
    public static <E extends Comparable<? super E>> void insertSort(List<E> list, Comparator<? super E> comparator) {
        if (null == list || list.isEmpty()) {
            return;
        }
        E temp;
        final int size = list.size();
        for (int i = 1; i < size; i++) {
            temp = list.get(i);
            for (int j = 0; j < i; j++) {
                if (comparator.compare(temp, list.get(j)) < 0) {
                    for (int k = i; k > j; k--) {
                        list.set(k, list.get(k - 1));
                    }
                    list.set(j, temp);
                    break;
                }
            }
        }
    }

    /**
     * 适用大数据量排序
     *
     * @param list
     *            待排序的集合
     * @param comparator
     *            比较器
     * @param <E>
     *            数据类型
     */
    public static <E> List<E> streamSort(List<E> list, Comparator<? super E> comparator) {
        if (null != list && !list.isEmpty()) {
            return list.stream().sorted(comparator).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * 使用二分算法(binary search)的方式 查找目标值第一次出现的位置，找到并返回该数据的下标 该目标元素可能发生重复
     * <p>
     * 如果没有找到，则返回-1，也就是一个非法的index
     *
     * @param list
     *            待搜索的list
     * @param targetValue
     *            搜索的目标值
     * @param <T>
     *            值类型
     * @return int 目标值的index
     */
    public static <T extends Comparable<? super T>> int binarySearchFirst(List<T> list, T targetValue) {
        if (list == null || list.isEmpty()) {
            return -1;
        }
        final int listSize = list.size();
        int leftIndex = 0;
        int targetIndex;
        int rightIndex = listSize - 1;
        while (leftIndex <= rightIndex) {
            int mid = leftIndex + (rightIndex - leftIndex) / 2;
            if (list.get(mid).compareTo(targetValue) >= 0) {
                rightIndex = mid - 1;
            } else {
                leftIndex = mid + 1;
            }
        }
        if (leftIndex < listSize && list.get(leftIndex).compareTo(targetValue) == 0) {
            targetIndex = leftIndex;
        } else {
            targetIndex = -1;
        }
        return targetIndex;
    }

    /**
     * 使用二分算法(binary search)的方式 查找目标值第一次出现的位置，找到并返回该数据的下标 该目标元素可能发生重复
     * <p>
     * 如果没有找到，则返回-1，也就是一个非法的index
     *
     * @param list
     *            待搜索的list
     * @param targetValue
     *            搜索的目标值
     * @param <T>
     *            值类型
     * @return int 目标值的index
     */
    public static <T> int binarySearchFirst(List<T> list, Comparator<? super T> comparator, T targetValue) {
        if (list == null || list.isEmpty()) {
            return -1;
        }
        final int listSize = list.size();
        int leftIndex = 0;
        int targetIndex;
        int rightIndex = listSize - 1;
        while (leftIndex <= rightIndex) {
            int mid = leftIndex + (rightIndex - leftIndex) / 2;
            if (comparator.compare(list.get(mid), targetValue) >= 0) {
                rightIndex = mid - 1;
            } else {
                leftIndex = mid + 1;
            }
        }
        if (leftIndex < listSize && comparator.compare(list.get(leftIndex), targetValue) == 0) {
            targetIndex = leftIndex;
        } else {
            targetIndex = -1;
        }
        return targetIndex;
    }

    /**
     * 使用二分算法(binary search)的方式 查找目标值最后一次出现的位置，找到并返回该数据的下标 该目标元素可能发生重复
     * <p>
     * 如果没有找到，则返回-1， 也就是一个非法的index
     *
     * @param list
     *            待搜索的list
     * @param targetValue
     *            搜索的目标值
     * @param <T>
     *            值类型
     * @param comparator
     *            比较器
     * @return int 目标值的index
     */
    public static <T> int binarySearchLast(List<T> list, Comparator<? super T> comparator, T targetValue) {
        if (list == null || list.isEmpty()) {
            return -1;
        }
        final int listSize = list.size();
        int leftIndex = 0;
        int targetIndex;
        int rightIndex = listSize - 1;
        while (leftIndex <= rightIndex) {
            int mid = leftIndex + (rightIndex - leftIndex) / 2;
            if (comparator.compare(list.get(mid), targetValue) <= 0) {
                leftIndex = mid + 1;
            } else {
                rightIndex = mid - 1;
            }
        }
        if (rightIndex >= 0 && comparator.compare(list.get(rightIndex), targetValue) == 0) {
            targetIndex = rightIndex;
        } else {
            targetIndex = -1;
        }
        return targetIndex;
    }

    /**
     * 使用二分算法(binary search)的方式 查找目标值最后一次出现的位置，找到并返回该数据的下标 该目标元素可能发生重复
     * <p>
     * 如果没有找到，则返回-1， 也就是一个非法的index
     *
     * @param list
     *            待搜索的list
     * @param targetValue
     *            搜索的目标值
     * @param <T>
     *            值类型
     * @return int 目标值的index
     */
    public static <T extends Comparable<? super T>> int binarySearchLast(List<T> list, T targetValue) {
        if (list == null || list.isEmpty()) {
            return -1;
        }
        final int listSize = list.size();
        int leftIndex = 0;
        int targetIndex;
        int rightIndex = listSize - 1;
        while (leftIndex <= rightIndex) {
            int mid = leftIndex + (rightIndex - leftIndex) / 2;
            if (list.get(mid).compareTo(targetValue) <= 0) {
                leftIndex = mid + 1;
            } else {
                rightIndex = mid - 1;
            }
        }
        if (rightIndex >= 0 && list.get(rightIndex).compareTo(targetValue) == 0) {
            targetIndex = rightIndex;
        } else {
            targetIndex = -1;
        }
        return targetIndex;
    }

    /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*
     *                            aggregation misc                                       *
     *+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    /**
     * 将list集合进行分组 key -> value 一对多
     *
     * @param list
     *            源数据
     * @param classifier
     *            指定生成对应key数据函数
     * @return list
     */
    public static <K, V> Map<K, List<V>> groupToMapList(List<V> list, Function<? super V, ? extends K> classifier) {
        return toMapList(list, classifier, e -> e);
    }

    /**
     * 将list集合进行分组并且生成指定value数据 key -> value 一对多
     *
     * @param list
     *            源数据
     * @param keyMapper
     *            指定生成对应key数据函数
     * @param valueMapper
     *            指定生成对应value数据函数
     * @return map
     */
    public static <K, V, U> Map<K, List<U>> toMapList(List<V> list, Function<? super V, ? extends K> keyMapper,
                                                      Function<? super V, ? extends U> valueMapper) {
        if (list == null || list.isEmpty()) {
            return new HashMap<>();
        }

        Map<K, List<U>> listMap = new HashMap<>();
        for (V v : list) {
            if (v == null) {
                continue;
            }
            K key = keyMapper.apply(v);
            U value = valueMapper.apply(v);
            List<U> vs = listMap.get(key);
            if (vs == null || vs.isEmpty()) {
                vs = new ArrayList<>();
            }
            vs.add(value);
            listMap.put(key, vs);
        }
        return listMap;
    }

    /**
     * 将list集合进行分组并且生成指定value数据 key -> value 一对一
     *
     * @param list
     *            源数据
     * @param keyMapper
     *            指定生成对应key数据函数
     * @param valueMapper
     *            指定生成对应value数据函数
     * @return map
     */
    public static <K, V, U> Map<K, U> toMap(List<V> list, Function<? super V, ? extends K> keyMapper,
                                            Function<? super V, ? extends U> valueMapper) {
        if (list == null || list.isEmpty()) {
            return new HashMap<>();
        }
        Map<K, U> toMap = new HashMap<>();
        for (V v : list) {
            K key = keyMapper.apply(v);
            U value = valueMapper.apply(v);
            toMap.putIfAbsent(key, value);
        }
        return toMap;
    }

    /**
     * 将list集合进行分组 key -> value 一对一
     *
     * @param list
     *            源数据
     * @param keyMapper
     *            指定生成对应key数据函数
     * @return map
     */
    public static <K, V> Map<K, V> toMap(List<V> list, Function<? super V, ? extends K> keyMapper) {
        return toMap(list, keyMapper, e -> e);
    }

    /**
     * 根据对应规则过滤指定集合数据并生成对应值集合
     *
     * @param list
     *            源数据
     * @param mapper
     *            指定生成对应数据函数
     * @param predicate
     *            过滤规则
     * @return list
     */
    public static <U, V> List<U> toList(List<V> list, Function<? super V, ? extends U> mapper,
                                        Predicate<? super V> predicate) {
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }
        List<U> toList = new ArrayList<>();
        for (V v : list) {
            U u = mapper.apply(v);
            if (v == null || u == null) {
                continue;
            }
            if (predicate == null) {
                toList.add(u);
            } else {
                if (predicate.test(v)) {
                    toList.add(u);
                }
            }
        }
        return toList;
    }

    /**
     * 指定集合数据并生成对应值集合
     *
     * @param list
     *            源数据
     * @param mapper
     *            指定生成对应数据函数
     * @return list
     */
    public static <U, V> List<U> toList(List<V> list, Function<? super V, ? extends U> mapper) {
        return toList(list, mapper, null);
    }

    /**
     * 根据规则过滤集合
     *
     * @param list
     *            源数据
     * @param predicate
     *            过滤规则
     * @return list
     */
    public static <V> List<V> filter(List<V> list, Predicate<? super V> predicate) {
        return toList(list, e -> e, predicate);
    }

    /**
     * 生成对应值集合并且保证值不重复
     *
     * @param list
     *            源数据
     * @param mapper
     *            指定生成对应数据函数
     * @return list
     */
    public static <U, V> List<U> toDistinctList(List<V> list, Function<? super V, ? extends U> mapper) {
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }
        List<U> toList = new ArrayList<>();
        for (V v : list) {
            U u = mapper.apply(v);
            if (v == null || u == null) {
                continue;
            }
            if (!toList.contains(u)) {
                toList.add(u);
            }
        }
        return toList;
    }

    /**
     * 集合排序获得对应值得集合
     *
     * @return sorted list
     */
    public static <U, V> List<U> sort(List<V> list, Function<? super V, ? extends U> mapper,
                                      Comparator<? super U> comparator) {
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }
        return list.stream().map(mapper).sorted(comparator).collect(Collectors.toList());
    }

    /**
     * 集合排序获取本身数据集合
     *
     * @return sorted list
     */
    public static <V> List<V> sort(List<V> list, Comparator<? super V> comparator) {
        return sort(list, e -> e, comparator);
    }
}
