(ns
  ^{:author raptor_MVK}
  tools.comb
  (:use tools.math)
  (:use tools.core))

(declare all-perms part-perms-count parts-by-coll-count perm-gen)

(defn all-perms
  "Given a sequence, returns all sequence permutations in lexicographical order
  presuming initial order of a sequence"
  [coll]
  (let [n (count coll)]
    (if (= n 1)
      [coll]
      (reduce into [] (map #(map (fn [m] (conj m (nth coll %)))
                              (all-perms (but-nth coll %))) (range n))))))

(defn part-perms-count
  "Given N, K, returns number of partial permutations of k elements from n"
  [n k]
  (reduce *' (map #(/ %1 %2)
               (reverse (range+ (inc (- n k)) n)) (rrange+ k))))

(defn parts-by-coll-count
  "Given N and collection, returns the number of partitions of N by elements of the
  collection"
  [n coll]
  (loop [cur-coll (take-while #(<= % n) coll)
         res (concat [1] (repeat n 0))]
    (if (empty? cur-coll)
      (last res)
      (recur (rest cur-coll)
        (let [cur (first cur-coll)]
          (loop [i cur
                 cur-res res]
            (if (> i n)
              cur-res
              (recur (inc i) (update-in (vec cur-res) [i]
                               #(+' % (nth cur-res (- i cur))))))))))))

(defn perm-gen
  "Given N, a sorted collection of digits, returns Nth lexicographical permutation of
  these digits"
  [n coll]
  (if (= 1 (count coll))
    coll
    (let [tail-perm-count (fact (dec (count coll)))
          digit (quot n tail-perm-count)]
      (concat [(nth coll digit)]
        (perm-gen (rem n tail-perm-count)
          (concat (take digit coll) (drop (inc digit) coll)))))))