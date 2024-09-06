--v3
--[[
jiewangshen
0
0
penxue
2
{default,qianjin,0,0,0,4,1.000000}{default,gongji4,1,0,5,96,1.000000}
1
{default,0.000000,0.000000,-200.000000,0.000000,0,1,0,4,0.000000,0,0,0,0}
0

0

1
{0,jiewangshen,2,0,0.000000,0.000000,91,0}
1
{default,texiao1,1,0,0,91,1.000000}
1
{default,0.000000,0.000000,-200.000000,0.000000,0,1,0,4,0.000000,0,0,0,0}
1
{default,1.500000,1.500000,1.500000,1.500000,0,0,1}
0


daiji
]]--
local skillTest = {
attack_type       = "jingong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"qianjin",1.000000,0,4,0},{"gongji4",1.000000,5,96,1}}, 
pos_sequence      = {{0.000000,0.000000,-200.000000,0.000000,0,1,0,4,0.000000,0,0,0,0}}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {{"jiewangshen",2,0,0.000000,0.000000,{{"texiao1",1.000000,0,91,1}},{{0.000000,0.000000,-200.000000,0.000000,0,1,0,4,0.000000,0,0,0,0}},{{1.500000,1.500000,1.500000,1.500000,0,0,1}},{},0}}, 
bloodNum          = 2,
flyAnim           = "ladeng",
flyAnim_equence   = {{"impact",1}}, 
par               = "",
fly_v             = 100,
fly_h             = 0,
hurtAnim          = "penxue",
hurtAnim_equence  = {{"impact",1}},
skillCallFunc     = function(self,battleSkill,battleTable) 
   local skillData = battleTable.m_currentFrameData; 
   table.foreach(skillData,function(k,v) 
   end); 
   local hpValue = 1;
   local function attackEnd()
   end
   battleSkill:createNormalAttack({skills_table = self,hpValue = skillData.hurt,animEndCallFunc = attackEnd});
end, 
}
return skillTest
