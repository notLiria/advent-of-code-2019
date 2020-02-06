(ns aoc-2019-day-3.core
  (:gen-class))


(defn string-to-wire
  [str]
  (map #(clojure.string/split % #"") (clojure.string/split str #",")))

(defn wire-hashes
  "Make wire into a vector of hashes {:dir :length}"
  [wire]
  (map #(hash-map :dir (first %) :len (Integer. (last %))) wire))

(defn filter-one-direction-from-hash
  "Filter all x coords from hashes"
  ([wire-hashes]
   (map :len (filter #(or (= "R" (:dir %)) (= "L" (:dir %))) wire-hashes)))
  ;; Filter all dir coords from hashes
  ([wire-hashes dir]
   (cond 
     (= dir "y") (map :len  (filter #(or (= "U" (:dir %)) (= "D" (:dir %))) wire-hashes))
     :else
     (map :len (filter #(or (= "R" (:dir %)) (= "L" (:dir %))) wire-hashes)))))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
