(:*******************************************************:)
(: Test: K-SeqBooleanFunc-11                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: fn:boolean() invoked on an non-empty xs:untypedAtomic should return false. :)
(:*******************************************************:)
not(boolean(xs:untypedAtomic("")))