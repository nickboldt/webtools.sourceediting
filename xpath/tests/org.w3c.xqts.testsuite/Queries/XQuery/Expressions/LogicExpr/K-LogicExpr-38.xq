(:*******************************************************:)
(: Test: K-LogicExpr-38                                  :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Empty xs:untypedAtomics have an EBV value of false. :)
(:*******************************************************:)
not(xs:untypedAtomic("a string") and xs:untypedAtomic(""))