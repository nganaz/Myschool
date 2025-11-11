package com.example.myschool.data

val physicsTopicsForm1 = listOf(
    Topic(id = "phy_f1_1", name = "Introduction to Physics", content = """
        1. What is Physics?
        Physics is the natural science that studies matter, its fundamental constituents, its motion and behavior through space and time, and the related entities of energy and force. Physics is one of the most fundamental scientific disciplines, and its main goal is to understand how the universe behaves.

        2. Branches of Physics
        Physics is a vast field that is typically divided into several branches. The major branches include:
        - **Classical Mechanics:** The study of the motion of objects and the forces that affect them. This includes fields like kinematics, dynamics, and statics.
        - **Thermodynamics:** The study of heat, work, and temperature, and their relation to energy, radiation, and physical properties of matter.
        - **Electromagnetism:** The study of electric and magnetic fields and their interactions with charged particles.
        - **Optics:** The study of the properties and behavior of light.
        - **Acoustics:** The study of sound and sound waves.
        - **Relativity:** A theory developed by Albert Einstein that describes the relationship between space, time, and gravity. It includes both special relativity and general relativity.
        - **Quantum Mechanics:** The study of the behavior of matter and energy at the atomic and subatomic levels.

        3. Physical Quantities and Units
        A physical quantity is a property of a material or system that can be quantified by measurement. Physical quantities can be categorized as either base quantities or derived quantities.
        - **Base Quantities:** These are fundamental quantities that are defined independently. The International System of Units (SI) defines seven base quantities:
            - Length (meter, m)
            - Mass (kilogram, kg)
            - Time (second, s)
            - Electric current (ampere, A)
            - Thermodynamic temperature (kelvin, K)
            - Amount of substance (mole, mol)
            - Luminous intensity (candela, cd)
        - **Derived Quantities:** These are quantities that are derived from the base quantities. Examples include velocity (length/time), force (mass * acceleration), and pressure (force/area).

        4. Measurement and Errors
        Measurement is the process of obtaining the magnitude of a quantity, such as length or mass, relative to a unit of measurement.
        - **Accuracy vs. Precision:**
            - **Accuracy** is how close a measured value is to the actual (true) value.
            - **Precision** is how close the measured values are to each other.
        - **Types of Errors:**
            - **Systematic Errors:** Errors that are consistent and repeatable. They are typically caused by faulty equipment or a flawed experimental method.
            - **Random Errors:** Errors that are unpredictable and fluctuate. They can be caused by limitations of the measurement instrument or environmental factors.
            - **Personal Errors:** Mistakes made by the experimenter, such as parallax error in reading a scale.
        - **Minimizing Errors:** Errors in measurement can be minimized by using more accurate instruments, repeating measurements, and applying correction factors.
        """.trimIndent()),
    Topic(id = "phy_f1_2", name = "Mechanics", content = """
        1. Motion
        Motion is the change in position of an object over time. It is one of the key concepts in classical mechanics.
        - **Types of Motion:** Motion can be classified into several types, including:
            - **Linear Motion:** Motion in a straight line (e.g., a car moving on a straight road).
            - **Circular Motion:** Motion along a circular path (e.g., the Earth revolving around the Sun).
            - **Oscillatory Motion:** Repetitive motion back and forth around a central position (e.g., a swinging pendulum).
        - **Key Concepts:**
            - **Distance:** The total path length covered by an object.
            - **Displacement:** The shortest distance between the initial and final positions of an object (a vector quantity).
            - **Speed:** The rate of change of distance (a scalar quantity).
            - **Velocity:** The rate of change of displacement (a vector quantity).
            - **Acceleration:** The rate of change of velocity (a vector quantity).

        2. Forces
        A force is a push or pull that can cause an object with mass to change its velocity (i.e., to accelerate).
        - **Newton's Laws of Motion:**
            - **First Law (Law of Inertia):** An object remains at rest or in uniform motion in a straight line unless acted upon by an external force.
            - **Second Law:** The acceleration of an object is directly proportional to the net force acting on it and inversely proportional to its mass (F = ma).
            - **Third Law:** For every action, there is an equal and opposite reaction.
        - **Types of Forces:**
            - **Gravitational Force:** The force of attraction between two objects with mass.
            - **Frictional Force:** The force that opposes motion between two surfaces in contact.
            - **Normal Force:** The support force exerted by a surface on an object resting on it.

        3. Work, Energy, and Power
        - **Work:** In physics, work is done when a force acting on an object causes a displacement of the object. It is calculated as the product of the force and the distance moved in the direction of the force (W = F * d).
        - **Energy:** The capacity to do work. Energy exists in various forms, including:
            - **Kinetic Energy (KE):** The energy of motion (KE = 1/2 * mv²).
            - **Potential Energy (PE):** Stored energy due to an object's position or state (e.g., gravitational potential energy, PE = mgh).
        - **Power:** The rate at which work is done or energy is transferred (P = W / t).

        4. Simple Machines
        Simple machines are mechanical devices that change the direction or magnitude of a force. The six classical simple machines are:
        - **Lever:** A rigid bar that pivots around a fixed point (fulcrum).
        - **Pulley:** A wheel on an axle or shaft that is designed to support movement and change of direction of a taut cable or belt.
        - **Inclined Plane:** A flat supporting surface tilted at an angle, with one end higher than the other.
        - **Wedge:** A triangular-shaped tool, often used to separate two objects or portions of an object.
        - **Screw:** A mechanism that converts rotational motion to linear motion, and a torque to a linear force.
        - **Wheel and Axle:** A wheel attached to a smaller axle so that these two parts rotate together.
        """.trimIndent()),
    Topic(id = "phy_f1_3", name = "Properties of Matter", content = """
        1. States of Matter
        Matter is anything that has mass and takes up space. It exists in several states, with the most common being solid, liquid, gas, and plasma.
        - **Solids:** Have a definite shape and volume. The particles are tightly packed in a fixed arrangement and vibrate about their fixed positions.
        - **Liquids:** Have a definite volume but no definite shape. They take the shape of the container they are in. The particles are close together but can move past one another.
        - **Gases:** Have no definite shape or volume. They expand to fill the entire container they are in. The particles are far apart and move randomly and rapidly.
        - **Plasma:** An ionized gas, consisting of a mixture of electrons and ions. It is the most abundant state of matter in the universe (e.g., stars).

        2. Density and Pressure
        - **Density:** A measure of how much mass is contained in a given volume. It is calculated as mass per unit volume (ρ = m/V).
            - **Units:** The SI unit for density is kilograms per cubic meter (kg/m³).
        - **Pressure:** The force exerted per unit area. It is calculated as force divided by the area over which the force is distributed (P = F/A).
            - **Units:** The SI unit for pressure is the pascal (Pa), which is one newton per square meter (N/m²).
            - **Atmospheric Pressure:** The pressure exerted by the weight of the atmosphere.

        3. Surface Tension and Viscosity
        - **Surface Tension:** A property of the surface of a liquid that allows it to resist an external force. It is caused by the cohesive forces between liquid molecules.
            - **Examples:** Insects walking on water, the formation of spherical water droplets.
        - **Viscosity:** A measure of a fluid's resistance to flow. It describes the internal friction of a moving fluid.
            - **High Viscosity:** The fluid flows slowly (e.g., honey).
            - **Low Viscosity:** The fluid flows easily (e.g., water).
        """.trimIndent())
)

val physicsTopicsForm2 = listOf(
    Topic(id = "phy_f2_1", name = "Heat", content = """
        1. Temperature and Heat
        - **Temperature:** A measure of the average kinetic energy of the particles in a substance. It is measured in degrees Celsius (°C), Fahrenheit (°F), or Kelvin (K).
        - **Heat:** The transfer of thermal energy from a hotter object to a cooler object. Heat is measured in joules (J).

        2. Thermal Expansion
        - **Definition:** The tendency of matter to change its shape, area, and volume in response to a change in temperature.
        - **Linear, Area, and Volumetric Expansion:** Substances can expand in one, two, or three dimensions.
        - **Applications:** Bimetallic strips in thermostats, expansion joints in bridges.

        3. Heat Transfer
        - **Conduction:** The transfer of heat through a substance by direct contact of particles.
        - **Convection:** The transfer of heat through a fluid (liquid or gas) by the movement of the fluid itself.
        - **Radiation:** The transfer of heat through electromagnetic waves.

        4. Specific Heat Capacity
        - **Definition:** The amount of heat energy required to raise the temperature of one kilogram of a substance by one degree Celsius (or one Kelvin).
        - **Formula:** Q = mcΔT, where Q is the heat energy, m is the mass, c is the specific heat capacity, and ΔT is the change in temperature.
        """.trimIndent()),
    Topic(id = "phy_f2_2", name = "Light", content = """
        1. Reflection of Light
        - **Definition:** The bouncing back of light when it strikes a surface.
        - **Laws of Reflection:**
            - The incident ray, the reflected ray, and the normal to the surface all lie in the same plane.
            - The angle of incidence is equal to the angle of reflection.

        2. Refraction of Light
        - **Definition:** The bending of light as it passes from one medium to another.
        - **Snell's Law:** n₁sinθ₁ = n₂sinθ₂, where n is the refractive index and θ is the angle with respect to the normal.

        3. Lenses and Mirrors
        - **Lenses:**
            - **Converging (Convex) Lenses:** Thicker at the center, causing parallel light rays to converge to a focus.
            - **Diverging (Concave) Lenses:** Thinner at the center, causing parallel light rays to diverge.
        - **Mirrors:**
            - **Concave Mirrors:** Reflect light inward to one focal point.
            - **Convex Mirrors:** Reflect light outward.

        4. The Human Eye
        - **Function:** The eye works like a camera, with the lens focusing light onto the retina, which then sends signals to the brain.
        - **Common Defects:** Myopia (nearsightedness) and hyperopia (farsightedness).
        """.trimIndent()),
    Topic(id = "phy_f2_3", name = "Sound", content = """
        1. Production and Propagation of Sound
        - **Production:** Sound is produced by vibrating objects.
        - **Propagation:** Sound travels as a longitudinal wave, requiring a medium (solid, liquid, or gas) to travel.

        2. Characteristics of Sound
        - **Pitch:** Determined by the frequency of the sound wave.
        - **Loudness:** Determined by the amplitude of the sound wave.
        - **Timbre (Quality):** The quality of a sound that distinguishes different types of sound production.

        3. Reflection of Sound (Echoes)
        - **Echo:** A reflection of sound that arrives at the listener with a delay after the direct sound.
        - **Reverberation:** The persistence of sound in a particular space after the original sound is removed.

        4. Musical Instruments
        - **String Instruments:** Produce sound from vibrating strings (e.g., guitar, violin).
        - **Wind Instruments:** Produce sound from vibrating columns of air (e.g., flute, trumpet).
        - **Percussion Instruments:** Produce sound by being struck or scraped (e.g., drums, cymbals).
        """.trimIndent())
)

val physicsTopicsForm3 = listOf(
    Topic(id = "phy_f3_1", name = "Electricity and Magnetism", content = """
        1. Electric Charge and Current
        - **Electric Charge:** A fundamental property of matter that can be positive or negative.
        - **Electric Current:** The flow of electric charge, measured in amperes (A).

        2. Ohm's Law and Resistance
        - **Ohm's Law:** The voltage across a conductor is directly proportional to the current flowing through it, provided all physical conditions and temperature remain constant (V = IR).
        - **Resistance:** The opposition to the flow of electric current, measured in ohms (Ω).

        3. Electric Circuits
        - **Series Circuit:** Components are connected end-to-end, providing a single path for the current.
        - **Parallel Circuit:** Components are connected in parallel branches, providing multiple paths for the current.

        4. Magnetism
        - **Magnets:** Objects that produce a magnetic field.
        - **Magnetic Field:** A region around a magnetic material or a moving electric charge within which the force of magnetism acts.

        5. Electromagnetism
        - **Electromagnet:** A type of magnet in which the magnetic field is produced by an electric current.
        - **Electromagnetic Induction:** The production of an electromotive force (voltage) across an electrical conductor in a changing magnetic field.
        """.trimIndent()),
    Topic(id = "phy_f3_2", name = "Waves", content = """
        1. Types of Waves
        - **Transverse Waves:** The particles of the medium vibrate perpendicular to the direction of wave propagation (e.g., light waves, ripples on water).
        - **Longitudinal Waves:** The particles of the medium vibrate parallel to the direction of wave propagation (e.g., sound waves).

        2. Properties of Waves
        - **Amplitude:** The maximum displacement or distance moved by a point on a vibrating body or wave measured from its equilibrium position.
        - **Wavelength (λ):** The distance between two consecutive points in the same phase.
        - **Frequency (f):** The number of complete oscillations per unit time.
        - **Wave Speed (v):** The speed at which the wave propagates (v = fλ).

        3. The Electromagnetic Spectrum
        - **Definition:** The range of all types of electromagnetic radiation.
        - **Order of Increasing Frequency:** Radio waves, Microwaves, Infrared, Visible Light, Ultraviolet, X-rays, Gamma rays.
        """.trimIndent()),
    Topic(id = "phy_f3_3", name = "Modern Physics", content = """
        1. Radioactivity
        - **Definition:** The spontaneous emission of radiation from the nucleus of an unstable atom.
        - **Types of Radiation:** Alpha (α), Beta (β), and Gamma (γ) radiation.

        2. Nuclear Energy
        - **Nuclear Fission:** The splitting of a heavy nucleus into lighter nuclei, releasing a large amount of energy.
        - **Nuclear Fusion:** The process by which two light atomic nuclei combine to form a single heavier one, releasing massive amounts of energy.

        3. The Photoelectric Effect
        - **Definition:** The emission of electrons from a material when light of a certain frequency shines on it.
        - **Einstein's Explanation:** Light consists of discrete packets of energy called photons. The energy of a photon is proportional to its frequency (E = hf).
        """.trimIndent())
)

val physicsTopicsForm4 = listOf(
    Topic(id = "phy_f4_1", name = "Electronics", content = """
        1. Semiconductors
        - **Definition:** Materials with electrical conductivity between that of a conductor and an insulator.
        - **Doping:** The process of adding impurities to a semiconductor to modify its electrical properties.

        2. Diodes and Transistors
        - **Diodes:** Semiconductor devices that allow current to flow in one direction.
        - **Transistors:** Semiconductor devices used to amplify or switch electronic signals and electrical power.

        3. Logic Gates
        - **Definition:** Basic building blocks of a digital circuit.
        - **Common Logic Gates:** AND, OR, NOT, NAND, NOR, XOR.
        """.trimIndent()),
    Topic(id = "phy_f4_2", name = "Astrophysics", content = """
        1. The Solar System
        - **Components:** The Sun, planets, moons, asteroids, comets, and other celestial bodies.

        2. Stars and Galaxies
        - **Stars:** Luminous spheres of plasma held together by their own gravity.
        - **Galaxies:** Vast systems of stars, stellar remnants, interstellar gas, dust, and dark matter.
	
        3. The Universe
        - **Definition:** All of space and time and their contents.
        - **The Big Bang Theory:** The leading cosmological model for the observable universe from the earliest known periods through its subsequent large-scale evolution.
        """.trimIndent()),
    Topic(id = "phy_f4_3", name = "Fluid Dynamics", content = """
        1. Bernoulli's Principle
        - **Definition:** For an inviscid flow, an increase in the speed of the fluid occurs simultaneously with a decrease in pressure or a decrease in the fluid's potential energy.

        2. Archimedes' Principle
        - **Definition:** The upward buoyant force that is exerted on a body immersed in a fluid, whether fully or partially submerged, is equal to the weight of the fluid that the body displaces.

        3. Fluid Flow
        - **Laminar Flow:** Smooth, orderly flow of a fluid.
        - **Turbulent Flow:** Chaotic, irregular flow of a fluid.
        """.trimIndent())
)

fun getPhysicsTopicsForForm(form: String?): List<Topic> {
    return when (form) {
        "Form 1" -> physicsTopicsForm1
        "Form 2" -> physicsTopicsForm2
        "Form 3" -> physicsTopicsForm3
        "Form 4" -> physicsTopicsForm4
        else -> emptyList()
    }
}

fun getPhysicsTopic(topicId: String?): Topic? {
    return physicsTopicsForm1.find { it.id == topicId }
        ?: physicsTopicsForm2.find { it.id == topicId }
        ?: physicsTopicsForm3.find { it.id == topicId }
        ?: physicsTopicsForm4.find { it.id == topicId }
}
