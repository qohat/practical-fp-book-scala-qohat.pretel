Best Practices
- Value classes instead just primitive types in the classes signatures, but creating value classes could overload the memory and we can improve it using @newtypes.

Even when @newtype is powerfull it doesn't guarante the correct compile time validation of the fields, for that reason we could join the Refined types in order to create constraint in the values in compiling time validation.

Say that, we can focus in the runtime validation taking advantage of the applicative accumulative errors or failing fast using monand sequencing manner, it depends on what we need.

- Sequetial vs Concurrent
State monad is sequential, so in a concurrent context, it's not the best option, so the Atomic Ref could supply the solution for that situation.

- Error handling
Depending on the scenario we could use MonadError, ApplicativeError. It depends on how do you model your error schema. If you use Either etc.


Taggles Final - Finally Tagless
Aternative to “initial encoding promoted by Free Monads”
Excerpt From: Gabriel Volpe. “Practical FP in Scala.” Apple Books. 