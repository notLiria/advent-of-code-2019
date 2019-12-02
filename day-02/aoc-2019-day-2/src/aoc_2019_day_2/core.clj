(ns aoc-2019-day-2.core
  (:gen-class))

(def filename "commands.csv")
(def max-value 19690720)
(defn str->int
  [str]
  (Integer. str))

(defn parse-file
  [file]
  (into [] (map str->int (clojure.string/split (slurp file) #","))))

(defn update-arr-with-op
  "op-arr: Array of operations, i.e, next 4 values
   arr: The original array, make a copy/changes to this"
  [op-arr arr]
  (cond
    ; Halt immediately
    (= 99 (first op-arr)) arr
    ; Add 
    (= 1 (first op-arr)) (assoc arr (nth op-arr 3) 
                                (+ (nth arr (nth op-arr 1))
                                   (nth arr (nth op-arr 2))))
    (= 2 (first op-arr)) (assoc arr (nth op-arr 3) 
                                (* (nth arr (nth op-arr 1))
                                   (nth arr (nth op-arr 2))))))

(defn apply-ops
  "arr: Original command array"
  [arr]  
  (loop [pos 0
         ops (take 4 arr)
         updated-arr arr]
    (cond
      ; We reach the end, return the array
      (or
       (= pos (count updated-arr))
       (= 99 (first ops))) updated-arr
      ; Oh god this is ugly, fix it 
      :else
      (let [new-arr (update-arr-with-op ops updated-arr)] 
        (recur (+ pos 4)
               (drop (+ pos 4) (take (+ pos 4 4) new-arr))
               new-arr)))))

(defn find-noun
  [arr]
  (loop [noun 0
         mod-arr (apply-ops arr)]
    (cond
      (< max-value (first mod-arr)) (- noun 1)
      (> noun 99) noun
      :else
      (recur (+ noun 1) (apply-ops (assoc arr 1 (+ noun 1)))))))

(defn find-verb
  "Given noun fixed, find verb"
  [arr]
  (loop [verb 0
         mod-arr (apply-ops arr)]
    (cond 
      (< max-value (first mod-arr)) (- verb 1)
      (> verb 99) verb
      :else
      (recur (+ verb 1) (apply-ops (assoc arr 2 (+ verb 1)))))))


(defn find-noun-and-verb
  [arr]
  (let [temp-arr (assoc arr 1 (find-noun arr))]
    (assoc temp-arr 2 (find-verb temp-arr))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
