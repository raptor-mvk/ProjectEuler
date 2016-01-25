(ns
  ^{:author raptor_MVK}
  tools.core)

(declare but-nth get-by-indices max-index min-index n-subseqs range+ rrange rrange+
  rswitch switch take-drop-while zero-shift)

(defn but-nth
  "Given collection and N, returns collection with nth element removed"
  [coll n]
  (concat (take n coll) (drop (inc n) coll)))

(defn get-by-indices
  "Given a sequence and collection of indices, returns collection of elements from
  sequence with indices from collection"
  [coll indices]
  (map #(nth coll %) indices))

(defn max-index
  "Given sequence, returns index of maximal element"
  [coll]
  (first (apply max-key last (map-indexed vector coll))))

(defn min-index
  "Given sequence, returns index of minimal element"
  [coll]
  (first (apply min-key last (map-indexed vector coll))))

(defn n-subseqs
  "Given sequence and N, returns all subsequences of length N"
  [coll n]
  (partition n (apply interleave (take n (iterate rest coll)))))

(defn range+
  "Given N, returns (range (inc n));
  given K, N, returns (range k (inc n))"
  ([n]
    (range+ 0 n))
  ([k n]
    (range k (inc n))))

(defn rrange
  "Given N, returns (range 1 n);
  given no arguments returns (rest (range))"
  ([]
    (rest (range)))
  ([n]
    (range 1 n)))

(defn rrange+
  "Given N, returns (range 1 (inc n))"
  [n]
  (range 1 (inc n)))

(defn rswitch
  "Given N and a collection, returns collection with last N elements moved to the
  beginning"
  [n coll]
  (if (= n 1)
    (concat [(last coll)] (butlast coll))
    (concat (take-last n coll) (drop-last n coll))))

(defn switch
  "Given N and a collection, returns collection with first N elements moved to the end"
  [n coll]
  (if (= n 1)
    (concat (rest coll) [(first coll)])
    (concat (drop n coll) (take n coll))))

(defn take-drop-while
  "Given pred, pred2 and coll, returns (take-while pred2 (drop-while pred coll))"
  [pred pred2 coll]
  (take-while pred2 (drop-while pred coll)))

(defn zero-shift
  "Given N and a collection, returns collection with N zeroes added at the beginning"
  [n coll]
  (if (= n 1)
    (concat [0] coll)
    (concat (repeat n 0) coll)))

