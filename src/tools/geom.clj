(ns
  ^{:author "raptor_MVK"}
  tools.geom
  (:use tools.core)
  (:use tools.math))

(declare distance inside? length point+normal2line point+dirvect2line points2kline
  points2line reflect v*)

(defn distance
  "Given point (X,Y) and line (A,B,C), returns signed distance from point to line"
  [point line]
  (let [line-length (length line)]
    (/ (+ (last line) (reduce + (map #(* %1 %2) point line))) line-length)))

(defn inside?
  "Given a sequence of points, representing polygon, and a point, returns true, if
 point is inside polygon, and false otherwise"
  [coll point]
  (let [make-vector (fn [x y] (map #(- %1 %2) x y))
        point-vectors (map #(make-vector % point) coll)]
    (apply = (map #(sgn (v* %1 %2)) point-vectors (switch 1 point-vectors)))))

(defn length
  "Given vector (A,B), returns its length"
  [[a b]]
  (Math/sqrt (+ (* a a) (* b b))))

(defn point+dirvect2line
  "Given point (X,Y) and direction vector (A,B), returns line (A,B,C) that is parallel
  to direction vector and passing through point"
  [point [a b]]
  (point+normal2line point [(- b) a]))

(defn point+normal2line
  "Given point (X,Y) and normal vector (A,B), returns line (A,B,C) that is perpendicular
  to normal vector and passing through point"
  [point [a b :as normal]]
  (map #(* (sgn a) %) [a b (- (reduce + (map #(* %1 %2) point normal)))]))

(defn points2kline
  "Given points (X1, Y1), (X2, Y2), returns line (k,b) that pass through these
  points"
  [[x y :as point1] point2]
  (let [dirvect (map #(- %1 %2) point1 point2)
        k (/ (last dirvect) (first dirvect))]
    [k (- y (* k x))]))

(defn points2line
  "Given points (X1, Y1), (X2, Y2), returns line (A,B,C) that pass through these
  points"
  [point1 point2]
  (point+dirvect2line point1 (map #(- %1 %2) point1 point2)))

(defn reflect
  "Given point (X,Y) and line (A,B,C), returns point, reflected relatively to line"
  [point line]
  (let [line-length (length line)
        dist (distance point line)
        reflected-dirvect (map #(/ % line-length) (butlast line))
        reflection (fn [x a] (- x (* 2.0 dist a)))]
    (map #(reflection %1 %2) point reflected-dirvect)))

(defn v*
  "Given two vectors (A,B) and (C,D), returns their cross product"
  [[a b] [c d]]
  (- (* a d) (* b c)))
