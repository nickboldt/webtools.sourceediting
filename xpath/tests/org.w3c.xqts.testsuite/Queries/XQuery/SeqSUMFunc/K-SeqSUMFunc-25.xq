(:*******************************************************:)
(: Test: K-SeqSUMFunc-25                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:41+02:00                       :)
(: Purpose: A test whose essence is: `sum((1, 2, 3, xs:anyURI("a string"), xs:double("NaN")))`. :)
(:*******************************************************:)
sum((1, 2, 3, xs:anyURI("a string"), xs:double("NaN")))