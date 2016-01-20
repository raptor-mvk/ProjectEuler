(ns
  ^{:author raptor_MVK}
  tools.comb
  (:use tools.math)
  (:use tools.core))

(declare all-part-perms all-perms part-perms-count perm-gen)

(defn all-part-perms
  "Given K and a sequence, returns all sequence partial permutations of K elements in
  order, defined by initial order of a sequence"
  [k coll]
  (let [n (count coll)]
    (if (= k 1)
      (map list coll)
      (if (= n 1)
        [coll]
        (reduce into []
          (map #(map (fn [m] (conj m (nth coll %)))
                  (all-part-perms (dec k) (but-nth coll %))) (range n)))))))

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
