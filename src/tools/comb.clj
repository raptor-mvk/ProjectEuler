(ns
  ^{:author "raptor_MVK"}
  tools.comb
  (:use tools.math)
  (:use tools.core))

(declare all-part-perms all-perms mix-perms part-perms-count perm-gen sums-count)

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

(defn mix-perms
  "Given two sequences, returns all distinct sequences, consists of elements of both
  sequences preserving order of each of given sequences"
  [c d]
  (if (empty? c)
    [d]
    (if (empty? d)
      [c]
      (concat (map #(concat [(first c)] %) (mix-perms (rest c) d))
        (map #(concat [(first d)] %) (mix-perms c (rest d)))))))

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

(defn sums-count
  "Given N and collection, returns the number of ways to divide every N into piles with
  the sizes from the collection"
  [n coll]
  (let [upd (fn [res i shift]
              (update res i #(+' (if (nil? %) 0 %) (get res (- i shift) 0))))]
    (loop [cur-coll (take-while #(<= % n) coll)
           res (hash-map 0 1)]
      (if (empty? cur-coll)
        (get res n)
        (recur (rest cur-coll)
          (let [cur (first cur-coll)]
            (concat (take cur res) ())
            (reduce #(upd %1 %2 cur) res (range+ cur n))))))))
